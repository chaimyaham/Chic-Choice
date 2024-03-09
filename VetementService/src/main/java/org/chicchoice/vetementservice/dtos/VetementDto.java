package org.chicchoice.vetementservice.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.chicchoice.vetementservice.enums.Category;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link org.chicchoice.vetementservice.entities.Vetement}
 */
@Getter
@Setter
@Builder
public class VetementDto implements Serializable {
    @Positive
    Long id;
    String note;
    @PastOrPresent
    LocalDateTime date_d_ajout;
    @NotNull
    Category category;
    String marque;
    @NotNull
    Long mediaId;
    @NotNull
    Long userId;
    @NotNull
    Boolean favoris;
    List<EnsembleDto> ensembles;
}