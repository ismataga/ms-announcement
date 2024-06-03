package com.example.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;

@Configuration
public class GeneralSecurityConfig {
    private final CustomFilter customFilter;
    private final CustomFilterNew customFilterNew;

    private final CustomEntryPoint customEntryPoint;

    public GeneralSecurityConfig(CustomFilter customFilter, CustomFilterNew customFilterNew, CustomEntryPoint customEntryPoint) {
        this.customFilter = customFilter;
        this.customFilterNew = customFilterNew;
        this.customEntryPoint = customEntryPoint;
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers(POST, "/v1/account*").permitAll()
                                .requestMatchers("/v1/auth/log-in").permitAll()
                                .requestMatchers("/v1/auth/user-info").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(customFilterNew, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(Customizer.withDefaults());
//                .httpBasic(basic -> basic.authenticationEntryPoint(customEntryPoint));

        return httpSecurity.build();
    }
}

