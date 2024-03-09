package org.chicchoice.CouleurService.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.chicchoice.CouleurService.entities.ChangementCouleur;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link org.chicchoice.CouleurService.entities.Couleur}
 */
@Value
public class CouleurDto implements Serializable {
    Long id;
    @NotNull
    String nom;
    @NotNull
    String codeRgb;
    @NotNull
    String codeHex;
    List<ChangementCouleur> historiqueChangements;
}