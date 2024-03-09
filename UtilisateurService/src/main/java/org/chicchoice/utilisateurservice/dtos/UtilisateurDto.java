package org.chicchoice.utilisateurservice.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.chicchoice.utilisateurservice.enums.Sexe;
import org.chicchoice.utilisateurservice.enums.UtilisateurRole;

import java.io.Serializable;

/**
 * DTO for {@link org.chicchoice.utilisateurservice.entities.Utilisateur}
 */
@Value
public class UtilisateurDto implements Serializable {
    Long id;
    @NotNull
    String email;
    @NotNull
    String motDePasse;
    @NotNull
    String nom;
    @NotNull
    String prenom;
    @NotNull
    Sexe sexe;
    @NotNull
    UtilisateurRole role;
    @NotNull
    String localisation;
    String preferencesStyle;
}