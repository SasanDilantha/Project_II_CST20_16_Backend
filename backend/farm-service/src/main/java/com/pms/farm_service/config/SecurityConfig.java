package com.pms.farm_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated() // Require authentication for all requests
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()) // Customize JWT conversion if needed
                        )
                )
                .csrf().disable(); // Disabling CSRF protection if not using cookies for sessions (optional)
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Use the JWK Set URI, which typically ends with "/protocol/openid-connect/certs"
        String jwkSetUri = "http://127.0.0.1:7070/realms/PoultryFarmSystem/protocol/openid-connect/certs"; // Adjust to use the JWK Set URI
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // You can customize the converter here, e.g., to set roles or authorities
        return converter;
    }
}
