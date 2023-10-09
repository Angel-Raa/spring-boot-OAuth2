package com.github.angel.raa.module.mapper;

import com.github.angel.raa.module.persistence.entity.ClientApp;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;



@Component
public class ClientAppMapper {
    public static RegisteredClient toRegisteredClient(ClientApp clientApp){
        RegisteredClient  registeredClient = RegisteredClient.withId(clientApp.getClientId())
                .clientId(clientApp.getClientId())
                .clientSecret(clientApp.getClientSecret())
                .clientIdIssuedAt(new Date(System.currentTimeMillis()).toInstant())
                .clientAuthenticationMethods((clientAuth) -> {
                    clientApp.getClientAuthenticationMethods().stream()
                            .map(ClientAuthenticationMethod::new)
                            .forEach(clientAuth::add);
                })
                .authorizationGrantTypes((authGrandType) -> {
                    clientApp.getAuthorizationGrantTypes().stream()
                            .map(AuthorizationGrantType::new)
                            .forEach(authGrandType::add);
                })
                .redirectUris((uri) -> {
                    uri.addAll(clientApp.getRedirectUris());
                })
                .scopes((scopes) -> {
                    scopes.addAll(clientApp.getScopes());
                })
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationMill()))
                        .refreshTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationMill() * 4L))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(clientApp.isRequiredProofKey())
                        .build())
                .build();


        return registeredClient;


    }
}
