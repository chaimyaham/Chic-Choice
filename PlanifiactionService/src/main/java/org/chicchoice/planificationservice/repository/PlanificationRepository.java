package org.chicchoice.planificationservice.repository;

import org.chicchoice.planificationservice.entities.Planification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanificationRepository extends JpaRepository<Planification, Long> {

    Page<Planification> findAllByUtilisateurId(Long userId, Pageable pageable);
    List<Planification> findAllByUtilisateurId(Long utilisateurId);
    void deleteAllByUtilisateurId(Long utilisateurId);
    Optional<Planification> findFirstByDateDebutBetweenAndUtilisateurIdOrderByDateDebutAsc(LocalDateTime debut, LocalDateTime fin, Long utilisateurId);
    List<Planification> findByEnsemblesIdsContaining(Long ensembleId);
}