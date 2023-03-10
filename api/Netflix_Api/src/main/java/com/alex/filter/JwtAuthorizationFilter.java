package com.alex.filter;

import com.alex.config.SecurityConfig;
import com.alex.model.User;
import com.alex.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

import static com.alex.config.SecurityConfig.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Autowired
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request,response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String username = JWT
                .require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(header.replace(TOKEN_PREFIX,""))
                .getSubject();
        if (username != null) {
            User user = userService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(
                    username, null, user.getAuthorities());
        }
        return null;
    }
}
