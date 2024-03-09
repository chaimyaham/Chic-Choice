package org.chicchoice.CouleurService.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.chicchoice.CouleurService.entities.ChangementCouleur}
 */
@Value
public class ChangementCouleurDto implements Serializable {
    Long id;
    String changement;
    @NotNull
    @PastOrPresent
    LocalDateTime dateChangement;
    @NotNull
    CouleurDto couleur;
}