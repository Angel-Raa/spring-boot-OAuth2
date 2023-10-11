package com.github.angel.raa.module.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomOAuth2TokenCustomize implements OAuth2TokenCustomizer<JwtEncodingContext> {
    @Override
    public void customize(JwtEncodingContext context) {
        Authentication authentication = context.getPrincipal(); // username
        String tokenTypes = context.getTokenType().getValue();
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        if (tokenTypes.equals("access_token")) {
            context.getClaims().claim("permission", authorities);
        }


    }
}
