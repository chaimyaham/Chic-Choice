package org.chicchoice.meteoservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Meteo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ville;
    private String description;
    private double temperature;
}
