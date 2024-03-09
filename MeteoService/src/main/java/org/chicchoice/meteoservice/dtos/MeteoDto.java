package org.chicchoice.meteoservice.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.chicchoice.meteoservice.entities.Meteo}
 */
@Value
public class MeteoDto implements Serializable {
    Long id;
    String ville;
    String description;
    double temperature;
}