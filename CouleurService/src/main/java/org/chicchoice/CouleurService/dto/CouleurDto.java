package org.chicchoice.CouleurService.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link org.chicchoice.CouleurService.entities.Couleur}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouleurDto implements Serializable {
    @NotNull
    String id;
    @NotNull
    String nom;
    @NotNull
    String hex;
    @NotNull
    String red;
    @NotNull
    String green;
    @NotNull
    String blue;
}