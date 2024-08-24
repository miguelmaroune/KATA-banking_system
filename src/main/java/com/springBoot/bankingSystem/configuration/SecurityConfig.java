package com.springBoot.bankingSystem.configuration;

import com.springBoot.bankingSystem.security.BankingUserDetailsService;
import com.springBoot.bankingSystem.security.JwtAuthEntryPoint;
import com.springBoot.bankingSystem.security.JwtAuthFilter;
import com.springBoot.bankingSystem.security.JwtGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthEntryPoint authEntryPoint;
    private final BankingUserDetailsService bankingUserDetailsService;

    public SecurityConfig(JwtAuthEntryPoint authEntryPoint, BankingUserDetailsService bankingUserDetailsService, JwtGenerator jwtGenerator) {
        this.authEntryPoint = authEntryPoint;
        this.bankingUserDetailsService = bankingUserDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("api/auth/**").permitAll();
                    auth.anyRequest().authenticated();})
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(hbc -> hbc.authenticationEntryPoint(authEntryPoint))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthenticationFilter(){
        return new JwtAuthFilter();
    }
}
