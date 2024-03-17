package org.chicchoice.vetementservice.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.chicchoice.vetementservice.entities.Ensemble}
 */
@Getter
@Setter
@NoArgsConstructor
public class EnsembleRequestDto implements Serializable {
    @NotBlank
    String nomDeLEnsemble;
    @NotNull
    @Positive
    Long utilisateurId;
    @NotNull
    Boolean favoris;
}