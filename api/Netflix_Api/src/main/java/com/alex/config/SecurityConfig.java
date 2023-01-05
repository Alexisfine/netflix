package com.alex.config;

import com.alex.exception.RestAuthenticationEntryPoint;
import com.alex.filter.JwtAuthorizationFilter;
import com.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        jsr250Enabled = true,
        securedEnabled = true
)
public class SecurityConfig {
    public static final String SECRET = "secret";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";

    public static final Long EXPIRATION_TIME = 864000000L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/register";

    public static final String SIGN_UP_RELATED = "/api/users/register/**";

    public static final String TOKEN_URL = "/api/tokens";

    private UserService userService;
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(UserService userService, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.userService = userService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .userDetailsService(userService)
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, SIGN_UP_URL, SIGN_UP_RELATED).permitAll()
                .requestMatchers(HttpMethod.POST, TOKEN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(
                        authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), userService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
