package org.chicchoice.utilisateurservice.services;

import org.chicchoice.utilisateurservice.dtos.UtilisateurDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UtilisateurService {

    Page<UtilisateurDto> recupererToutsLesUtilisateur(Pageable pageable);
    UtilisateurDto recupererUtilisateurParEmail(String email);
    String signUpUser(UtilisateurDto signUpRequest);

}
