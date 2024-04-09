package com.simplon.planification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanificationDto implements Serializable {
    Long id;
    @NotNull(message = "La date de debut ne peut pas etre nul")
    LocalDateTime dateDebut;
    @NotNull(message = "La date de fin ne peut pas etre nul")
    LocalDateTime dateFin;
    @NotBlank(message = "La description ne peut pas etre vide")
    @Size(max = 255, message = "La description ne peut pas depasser 255 caracteres")
    String description;
    @NotNull(message = "L'id de l'utilisateur ne peut pas etre nul")
    Long utilisateurId;
    @NotNull(message = "L'id de la meteo ne peut pas etre nul")
    Long meteoId;
    List<Long> ensemblesIds;
}
