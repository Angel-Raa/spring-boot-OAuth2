package com.github.angel.raa.module.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class AuthorizationSecurityConfiguration {
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        http.exceptionHandling((exception) -> {
            exception.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
        });

        // Accept Tokens
        http.oauth2ResourceServer(oauth2ResourceServerConfig -> {
            oauth2ResourceServerConfig.jwt(Customizer.withDefaults());
        });
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultWebSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/login").permitAll();
            auth.anyRequest().authenticated();
        });
        http.formLogin(Customizer.withDefaults());
        return http.build();
    }


}
