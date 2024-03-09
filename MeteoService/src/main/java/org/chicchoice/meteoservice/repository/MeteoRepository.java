package org.chicchoice.meteoservice.repository;

import org.chicchoice.meteoservice.entities.Meteo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeteoRepository extends JpaRepository<Meteo, Long> {
}