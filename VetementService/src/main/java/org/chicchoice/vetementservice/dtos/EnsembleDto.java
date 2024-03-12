package org.chicchoice.vetementservice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link org.chicchoice.vetementservice.entities.Ensemble}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnsembleDto implements Serializable {
    @Positive
    Long id;
    LocalDateTime createdAt;
    @NotNull
    String nomDeLEnsemble;
    Long utilisateurId;
    Boolean favoris;
    @NotNull
    List<VetementDto> vetements;
}