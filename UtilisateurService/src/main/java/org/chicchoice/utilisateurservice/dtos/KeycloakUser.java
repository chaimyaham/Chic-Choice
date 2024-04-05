package org.chicchoice.utilisateurservice.dtos;

import lombok.Data;
import org.chicchoice.utilisateurservice.enums.Sexe;

@Data
public class KeycloakUser {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
    private Sexe sexe;
    private String country;
    private String  city;
    private String preferencesStyle;

}