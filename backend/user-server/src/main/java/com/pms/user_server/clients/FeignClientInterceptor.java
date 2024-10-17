package com.pms.user_server.clients;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getToken() != null) {
            String token = authentication.getToken().getTokenValue();
            requestTemplate.header("Authorization", "Bearer " + token);
            // Log the token for debugging
            System.out.println("Feign Client Token: " + token);
        } else {
            // Log if authentication is missing
            System.out.println("No authentication found in context.");
        }
    }
}
