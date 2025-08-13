package com.yzn.transaction_consumer.config;

import com.yzn.transaction_consumer.model.User;
import com.yzn.transaction_consumer.model.enums.Role;
import com.yzn.transaction_consumer.security.CustomUserDetailsService;
import com.yzn.transaction_consumer.security.JwtFilter;
import com.yzn.transaction_consumer.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    public SecurityConfig(JwtFilter jwtFilter, CustomUserDetailsService customUserDetailsService, @Lazy UserService userService) {
        this.jwtFilter = jwtFilter;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())  // Disable CSRF if using Postman and stateless JWT auth
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Public read access for all authenticated users
                        .requestMatchers(HttpMethod.GET, "/api/motifs/**").hasAnyRole("ADMIN","USER")

                        // Restricted to ADMIN only
                        .requestMatchers(HttpMethod.POST, "/api/motifs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/motifs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/motifs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/role").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/auth/**").permitAll()  // Permit all auth endpoints like login, register
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (!userService.userExists(adminUsername)) {
                User admin = new User(adminUsername,adminPassword,Role.ADMIN);
                userService.saveUser(admin);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}

