package com.example.jwt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.jwt.user.Permission.*;
import static com.example.jwt.user.Role.ADMIN;
import static com.example.jwt.user.Role.MANAGER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()

                //.requestMatchers("/api/v1/management/**")
                //.hasAnyRole(ADMIN.name(), MANAGER.name())
                .requestMatchers(HttpMethod.GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.getPermission(), MANAGER_READ.getPermission())
                .requestMatchers(HttpMethod.POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_WRITE.getPermission(), MANAGER_WRITE.getPermission())

                //.requestMatchers("/api/v1/admin/**")
                //.hasRole(ADMIN.name())
                /*
                .requestMatchers(HttpMethod.GET, "/api/v1/admin/**")
                .hasAuthority(ADMIN_READ.getPermission())
                .requestMatchers(HttpMethod.POST, "/api/v1/admin/**")
                .hasAuthority(ADMIN_WRITE.getPermission())
                */

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
