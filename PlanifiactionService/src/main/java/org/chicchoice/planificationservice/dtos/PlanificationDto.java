package org.chicchoice.planificationservice.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.chicchoice.planificationservice.entities.Planification}
 */
@Value
public class PlanificationDto implements Serializable {
    Long id;
    @FutureOrPresent
    LocalDate datePlanification;
    Long userId;
    Long EnsembleId;
}