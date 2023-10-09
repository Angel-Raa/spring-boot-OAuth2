package com.github.angel.raa.module.persistence.repository;

import com.github.angel.raa.module.persistence.entity.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClientAppRepository extends JpaRepository<ClientApp, Long> {
    Optional<ClientApp> findByClientId(String clientId);
}
