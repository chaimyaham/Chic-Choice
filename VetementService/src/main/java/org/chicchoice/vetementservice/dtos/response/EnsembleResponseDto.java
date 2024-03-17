package org.chicchoice.vetementservice.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.chicchoice.vetementservice.enums.Category;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link org.chicchoice.vetementservice.entities.Ensemble}
 */
@Getter
@Setter
@NoArgsConstructor
public class EnsembleResponseDto implements Serializable {
    Long id;
    LocalDateTime createdAt;
    String nomDeLEnsemble;
    Long utilisateurId;
    Boolean favoris;
    List<VetementDto> vetements;

    /**
     * DTO for {@link org.chicchoice.vetementservice.entities.Vetement}
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class VetementDto implements Serializable {
        Long id;
        String note;
        LocalDateTime date_d_ajout;
        Category category;
        String marque;
        Long mediaId;
        Long userId;
        Boolean favoris;
    }
}