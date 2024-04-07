package org.chicchoice.utilisateurservice.services.impl;

import org.chicchoice.utilisateurservice.dtos.KeycloakUser;
import org.chicchoice.utilisateurservice.dtos.UtilisateurDto;
import org.chicchoice.utilisateurservice.mapper.UtilisateurMapper;
import  org.chicchoice.utilisateurservice.exceptions.UtilisateurNotFoundException;
import org.chicchoice.utilisateurservice.entities.Utilisateur;
import org.chicchoice.utilisateurservice.repository.UtilisateurRepository;
import org.chicchoice.utilisateurservice.services.KeycloakService;
import org.chicchoice.utilisateurservice.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final KeycloakService keycloakService;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper, KeycloakService keycloakService) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
        this.keycloakService = keycloakService;
    }


    @Override
    public UtilisateurDto recupererUtilisateurParEmail(String email) {
        try {
            Optional<Utilisateur> utilisateur = Optional.ofNullable(utilisateurRepository.findUtilisateursByEmail(email)
                    .orElseThrow(() -> new UtilisateurNotFoundException("User not found with that email: ", email)));
            LOGGER.info("user recuperer avec success: {}", utilisateur.get().getEmail());
            return utilisateurMapper.toDto(utilisateur.get());
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving user with id: " + id, e);
            throw new RuntimeException("Error occurred while retrieving user with id: " + id, e);
        }
    }

    @Override
    public String signUpUser(UtilisateurDto signUpRequest) {
        LOGGER.info("UserServiceImpl | signUpUser is started");
        KeycloakUser keycloakUser = new KeycloakUser();
        keycloakUser.setFirstName(signUpRequest.getPrenom());
        keycloakUser.setLastName(signUpRequest.getNom());
        keycloakUser.setEmail(signUpRequest.getEmail());
        keycloakUser.setPassword(signUpRequest.getPassword());
        keycloakUser.setRole(signUpRequest.getRole().name());
        keycloakUser.setUsername(signUpRequest.getUsername());
        keycloakUser.setSexe(signUpRequest.getSexe());
        keycloakUser.setCity(signUpRequest.getVille());
        keycloakUser.setCountry(signUpRequest.getPays());
        keycloakUser.setPreferencesStyle(signUpRequest.getPreferencesStyle());

        int status = keycloakService.createUserWithKeycloak(keycloakUser);

        if (status == 201) {

            LOGGER.info("UserServiceImpl | signUpUser | status : {}", status);
            Utilisateur signUpUser = utilisateurMapper.toEntity(signUpRequest);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(signUpRequest.getPassword());
            signUpUser.setPassword(hashedPassword);
            utilisateurRepository.save(signUpUser);
            return "Sign Up completed";
        }

        return "Not Register";
    }
    @Override
    public Page<UtilisateurDto> recupererToutsLesUtilisateur(Pageable pageable) {
        try {
            Page<Utilisateur> utilisateurs = utilisateurRepository.findAll(pageable);
            LOGGER.info("Retrieved {} users", utilisateurs.getTotalElements());
            return utilisateurs.map(utilisateurMapper::toDto);
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving users", e);
            throw new RuntimeException("Error occurred while retrieving users", e);
        }
    }

}

