package org.chicchoice.utilisateurservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    Long id;

    @NotNull
    @Email
    String email;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern.List({
            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit"),
            @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter"),
            @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter"),
            @Pattern(regexp = ".*[!@#$%^&*()\\-_=+{};:,<.>\\[\\]\\\\|/?~].*", message = "Password must contain at least one special character")
    })
    String password;

    @NotNull
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long")
    String nom;

    @NotNull
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters long")
    String prenom;

    @NotNull
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters long")
    String username;

    @NotNull
    Sexe sexe;

    @NotNull
    UtilisateurRole role;

    @NotNull
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters long")
    String ville;

    @NotNull
    @Size(min = 2, max = 50, message = "Country must be between 2 and 50 characters long")
    String pays;

    @Size(max = 100, message = "Preferences style cannot exceed 100 characters")
    String preferencesStyle;
}