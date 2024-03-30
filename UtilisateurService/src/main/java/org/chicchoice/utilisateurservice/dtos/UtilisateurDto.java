package org.chicchoice.utilisateurservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.chicchoice.utilisateurservice.enums.Sexe;
import org.chicchoice.utilisateurservice.enums.UtilisateurRole;

import java.io.Serializable;

/**
 * DTO for {@link org.chicchoice.utilisateurservice.entities.Utilisateur}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurDto implements Serializable {
    @Positive(message = "L'identifiant doit etre un nombre positif.")
    Long id;

    @NotBlank(message = "L'adresse email ne peut pas etre vide.")
    @Email(message = "L'adresse email doit etre valide.")
    String email;

    @NotBlank(message = "Le mot de passe ne peut pas etre vide.")
    String motDePasse;

    @NotBlank(message = "Le nom ne peut pas etre vide.")
    String nom;

    @NotBlank(message = "Le prenom ne peut pas etre vide.")
    String prenom;

    @NotNull(message = "Le sexe ne peut pas etre vide.")
    Sexe sexe;

    @NotNull(message = "Le role ne peut pas etre vide.")
    UtilisateurRole role;

    @NotBlank(message = "La ville ne peut pas etre vide.")
    String ville;

    @NotBlank(message = "Le pays ne peut pas etre vide.")
    String pays;

    @NotBlank(message = "Les preferences de style ne peuvent pas etre vides.")
    String preferencesStyle;
}