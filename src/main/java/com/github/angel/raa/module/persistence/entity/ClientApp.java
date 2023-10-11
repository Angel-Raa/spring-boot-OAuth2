package com.github.angel.raa.module.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_app")
public class ClientApp implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "client_secret")
    private String clientSecret;
    @Column(name = "client_app_client_authentication_methods")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> clientAuthenticationMethods;
    @Column(name = "client_app_authorization_grant_types")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorizationGrantTypes;
    @Column(name = "client_app_redirect_uris")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> redirectUris;
    @Column(name = " client_app_scopes")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> scopes;
    @Column(name = "duration_in_minutes")
    private int durationMill;
    @Column(name = "required_proof_key")
    private boolean requiredProofKey;


}
