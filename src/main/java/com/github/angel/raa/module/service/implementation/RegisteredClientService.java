package com.github.angel.raa.module.service.implementation;

import com.github.angel.raa.module.exception.UserNotFoundException;
import com.github.angel.raa.module.mapper.ClientAppMapper;
import com.github.angel.raa.module.persistence.entity.ClientApp;
import com.github.angel.raa.module.persistence.repository.IClientAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new UserNotFoundException("client not find"));
        return ClientAppMapper.toRegisteredClient(clientApp);
    }

    @Override
    @Transactional
    public RegisteredClient findByClientId(String clientId) {
        return this.findById(clientId);
    }
}
