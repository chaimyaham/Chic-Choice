package org.chicchoice.utilisateurservice.repository;

import org.chicchoice.utilisateurservice.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}