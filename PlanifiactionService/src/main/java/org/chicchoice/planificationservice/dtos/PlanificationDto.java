package org.chicchoice.planificationservice.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.chicchoice.planificationservice.entities.Planification;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Planification}
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class PlanificationDto implements Serializable {
    @Positive
    Long id;
    @NotNull
    String titre;
    @NotNull
    @FutureOrPresent
    LocalDate dateDebut;
    @Future
    LocalDate dateFin;
    String description;
    @NotNull
    Long utilisateurId;
    @NotNull
    List<Long> ensemblesIds;
}