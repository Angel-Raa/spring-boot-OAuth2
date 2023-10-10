package com.github.angel.raa.module.service.implementation;

import com.github.angel.raa.module.exception.UserNotFoundException;
import com.github.angel.raa.module.mapper.ClientAppMapper;
import com.github.angel.raa.module.persistence.entity.ClientApp;
import com.github.angel.raa.module.persistence.repository.IClientAppRepository;
import com.github.angel.raa.module.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisteredClientService implements RegisteredClientRepository {
    private final IClientAppRepository clientAppRepository;
    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    @Transactional
    public RegisteredClient findById(String id) {
      ClientApp clientApp = clientAppRepository.findByClientId(id)
                .orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND, 404, HttpStatus.NOT_FOUND, LocalDateTime.now()));
        return ClientAppMapper.toRegisteredClient(clientApp);
    }

    @Override
    @Transactional
    public RegisteredClient findByClientId(String clientId) {
        return this.findById(clientId);
    }
}
