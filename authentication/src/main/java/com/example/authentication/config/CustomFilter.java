package com.example.authentication.config;


import com.example.authentication.service.TokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CustomFilter extends OncePerRequestFilter {
    public static final String BEARER = "Bearer";
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Work Custom filter");
        try {
            String token = request.getHeader("Authorization");
            log.info("Token is: {}", token);
            if (token != null && token.startsWith(BEARER)) {
                String tokenWithoutBearer = token.substring(BEARER.length());

                Date date = jwtService.extractClaims(tokenWithoutBearer, Claims::getExpiration);
                if (date.before(new Date())) return;

                Claims claims = jwtService.tokenParser(tokenWithoutBearer);
                Authentication authentication = jwtService.getAuthentication(claims);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}
