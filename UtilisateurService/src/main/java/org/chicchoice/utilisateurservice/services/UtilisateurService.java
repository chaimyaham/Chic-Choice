package org.chicchoice.utilisateurservice.services;

import org.chicchoice.utilisateurservice.dtos.UtilisateurDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UtilisateurService {
//    UtilisateurDto creeUtilisateur(UtilisateurDto utilisateurDto);
//    UtilisateurDto modifierUtilisiteur(Long id,UtilisateurDto utilisateurDto);
//    Page<UtilisateurDto> recupererToutsLesUtilisateur(Pageable pageable);
//    void supprimerUtilisateur(Long id);
//    UtilisateurDto recupererUtilisateurParId(Long id);
    String signUpUser(UtilisateurDto signUpRequest);
}
