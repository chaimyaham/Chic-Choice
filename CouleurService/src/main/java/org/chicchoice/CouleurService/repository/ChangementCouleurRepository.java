package org.chicchoice.CouleurService.repository;

import org.chicchoice.CouleurService.entities.ChangementCouleur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangementCouleurRepository extends JpaRepository<ChangementCouleur, Long> {
}