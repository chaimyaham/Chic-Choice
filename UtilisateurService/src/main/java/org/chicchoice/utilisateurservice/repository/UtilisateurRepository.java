package org.chicchoice.utilisateurservice.repository;

import org.chicchoice.utilisateurservice.dtos.UtilisateurDto;
import org.chicchoice.utilisateurservice.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    boolean existsByEmail(String email);
    Optional<Utilisateur> findUtilisateursByEmail(String email);
}