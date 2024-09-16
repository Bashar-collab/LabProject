package com.example.laboratory.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disabling the default form login
// Configuring form login and logout, and permit all users to access the signup page
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/**").permitAll() // Allow access to the signup page
                        .anyRequest().authenticated() // Require authentication for any other request
                )
                .formLogin((form) -> form
                        .loginPage("/signup")
                        .permitAll() // Allow all users to see the login page
                )
                .logout(LogoutConfigurer::permitAll);

        // Disabling default form login and HTTP Basic authentication
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

 */
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection if you're allowing unrestricted access

        // Allow access to all requests without authentication
        .authorizeHttpRequests(authz -> authz
            .anyRequest().permitAll()  // Permit all requests without requiring authentication
        )

        // Disable form login and HTTP Basic authentication
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable);

    return http.build();
}
}



