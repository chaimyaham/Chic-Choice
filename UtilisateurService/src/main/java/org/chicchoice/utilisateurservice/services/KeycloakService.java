package org.chicchoice.utilisateurservice.services;

import org.chicchoice.utilisateurservice.dtos.LoginRequest;
import org.keycloak.representations.AccessTokenResponse;
import org.chicchoice.utilisateurservice.dtos.KeycloakUser;

public interface KeycloakService {
    public AccessTokenResponse loginWithKeycloak(LoginRequest request);
    public int createUserWithKeycloak(KeycloakUser keycloakUser);
}
