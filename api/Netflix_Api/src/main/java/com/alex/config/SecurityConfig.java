package com.alex.config;

import com.alex.exception.RestAuthenticationEntryPoint;
import com.alex.filter.JwtAuthenticationFilter;
import com.alex.filter.JwtAuthorizationFilter;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.alex.config.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private UserService userDetailsService;

    @Autowired
    public SecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint, UserService userService) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.userDetailsService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(
                        new JwtAuthenticationFilter(
                                authenticationManager(
                                        http.getSharedObject(AuthenticationConfiguration.class))))
                .addFilter(
                        new JwtAuthorizationFilter(
                                authenticationManager(
                                        http.getSharedObject(AuthenticationConfiguration.class)
                                )
                        ))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
}
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
