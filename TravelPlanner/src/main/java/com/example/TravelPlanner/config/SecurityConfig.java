//package com.example.TravelPlanner.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**");
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/", "/login", "/signup/member","/signup/memberid-check","/search/**","/member/travelplans/**"
//                                ,"/comments/**"
//                                ,"/js/**"
//                                ,"/css/**"
//                                ,"/main.js").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "MEMBER")
//                        .anyRequest().authenticated()
//
//                );
//
//        return http.build();
//    }
//}