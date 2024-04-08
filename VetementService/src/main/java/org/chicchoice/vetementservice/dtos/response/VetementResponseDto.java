package org.chicchoice.vetementservice.dtos.response;

import lombok.Value;
import org.chicchoice.vetementservice.enums.Category;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link org.chicchoice.vetementservice.entities.Vetement}
 */
@Value
public class VetementResponseDto implements Serializable {
    Long id;
    String note;
    LocalDateTime date_d_ajout;
    Category category;
    String marque;
    Long mediaId;
    Long userId;
    Boolean favoris;
    String couleurId;
    List<EnsembleDto> ensembles;

    /**
     * DTO for {@link org.chicchoice.vetementservice.entities.Ensemble}
     */
    @Value
    public static class EnsembleDto implements Serializable {
        Long id;
        LocalDateTime createdAt;
        String nomDeLEnsemble;
        Long utilisateurId;
        Boolean favoris;
    }
}