package org.chicchoice.vetementservice.repositories;

import org.chicchoice.vetementservice.entities.Vetement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetementRepository extends JpaRepository<Vetement, Long> {

    Optional<Vetement> findByMediaId(Long aLong);
}