package org.chicchoice.vetementservice.repositories;

import org.chicchoice.vetementservice.entities.Ensemble;
import org.chicchoice.vetementservice.entities.Vetement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnsembleRepository extends JpaRepository<Ensemble, Long> {
    Optional<Page<Ensemble>> findAllByUtilisateurId(Long utilisateurId, Pageable pageable);
    Optional<Page<Ensemble>> findAllByUtilisateurIdAndFavoris(Long userId,boolean favoris,Pageable pageable);
    List<Ensemble> findByVetementsContaining(Vetement vetement);
}