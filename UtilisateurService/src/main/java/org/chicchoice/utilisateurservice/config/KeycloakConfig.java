package org.chicchoice.utilisateurservice.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    public final static String serverUrl = "http://localhost:7080";
    public final static String realm = "chic-choice";
    public final static String clientId = "chichoice-app";
    public final static String clientSecret = "SR0xpaFdZscvEfrqHyKLta69LIPEpoWg";
    final static String userName = "admin";
    final static String password = "admin";

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public Keycloak keycloak(){
        return Keycloak.getInstance(serverUrl,
                realm,
                userName,
                password,
                clientId,
                clientSecret);
    }
}