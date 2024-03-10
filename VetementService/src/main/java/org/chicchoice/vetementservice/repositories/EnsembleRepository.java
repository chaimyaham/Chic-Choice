package org.chicchoice.vetementservice.repositories;

import org.chicchoice.vetementservice.entities.Ensemble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsembleRepository extends JpaRepository<Ensemble, Long> {
}