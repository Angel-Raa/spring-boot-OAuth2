package com.github.angel.raa.module.configuration.security;

import com.github.angel.raa.module.exception.UserNotFoundException;
import com.github.angel.raa.module.persistence.repository.UserRepository;
import com.github.angel.raa.module.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class SecurityBeansInjector {
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return (username) -> userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(Message.CLIENT_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
    }

}
