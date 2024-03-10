package org.chicchoice.vetementservice.repositories;

import org.chicchoice.vetementservice.entities.Vetement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetementRepository extends JpaRepository<Vetement, Long> {
}