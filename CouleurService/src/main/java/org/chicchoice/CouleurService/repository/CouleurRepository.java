package org.chicchoice.CouleurService.repository;

import org.chicchoice.CouleurService.entities.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouleurRepository extends JpaRepository<Couleur, Long> {
}