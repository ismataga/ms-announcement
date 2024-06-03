//package com.example.userms.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//
//@Configuration
//@Import({CustomFilter.class, CustomEntryPoint.class, JwtService.class})
//public class SecurityConfig extends GeneralSecurityConfig {
//
//    public SecurityConfig(CustomFilter customFilter, CustomEntryPoint customEntryPoint) {
//        super(customFilter, customEntryPoint);
//    }
//
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests((auth) ->
//                auth.requestMatchers("/v1/users/register").permitAll()
//        );
//        return super.configure(httpSecurity);
//    }
//}
