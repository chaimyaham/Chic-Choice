package org.chicchoice.utilisateurservice.services.impl;

import org.chicchoice.utilisateurservice.dtos.KeycloakUser;
import org.chicchoice.utilisateurservice.dtos.UtilisateurDto;
import org.chicchoice.utilisateurservice.mapper.UtilisateurMapper;
import org.chicchoice.utilisateurservice.exceptions.EmailAlreadyExistsException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final KeycloakService keycloakService;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,UtilisateurMapper utilisateurMapper,KeycloakService keycloakService) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper=utilisateurMapper;
        this.keycloakService=keycloakService;
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

        int status = keycloakService.createUserWithKeycloak(keycloakUser);

        if(status == 201){

            LOGGER.info("UserServiceImpl | signUpUser | status : {}" , status);
            Utilisateur signUpUser = utilisateurMapper.toEntity(signUpRequest);
            utilisateurRepository.save(signUpUser);
            return "Sign Up completed";
        }

        return "Not Register";
    }
    }

//    @Override
//    public UtilisateurDto creeUtilisateur(UtilisateurDto utilisateurDto) {
//        try {
//            if (utilisateurRepository.existsByEmail(utilisateurDto.getEmail())) {
//                logger.warn("email already exist : {}",utilisateurDto.getEmail());
//                throw new EmailAlreadyExistsException("Email already exists: " + utilisateurDto.getEmail());
//            }
//            Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);
//            utilisateur = utilisateurRepository.save(utilisateur);
//            logger.info("User created successfully with id: {}", utilisateur.getId());
//            return utilisateurMapper.toDto(utilisateur);
//        } catch (Exception e) {
//            logger.error("Error occurred while creating user", e);
//            throw new RuntimeException("Error occurred while creating user", e);
//        }
//    }
//
//    @Override
//    public UtilisateurDto modifierUtilisiteur(Long id, UtilisateurDto utilisateurDto) {
//        try {
//            Utilisateur utilisateur = utilisateurRepository.findById(id)
//                    .orElseThrow(() -> new UtilisateurNotFoundException("User not found with id: " , id.toString()));
//            utilisateur.setPassword(utilisateurDto.getPassword());
//            utilisateur.setPays(utilisateurDto.getPays());
//            utilisateur.setVille(utilisateurDto.getVille());
//
//            utilisateur = utilisateurRepository.save(utilisateur);
//            logger.info("User updated successfully with id: {}", utilisateur.getId());
//            return utilisateurMapper.toDto(utilisateur);
//        } catch (Exception e) {
//            logger.error("Error occurred while modifying user with id: " + id, e);
//            throw new RuntimeException("Error occurred while modifying user with id: " + id, e);
//        }
//    }
//
//
//
//    @Override
//    public Page<UtilisateurDto> recupererToutsLesUtilisateur(Pageable pageable) {
//        try {
//            Page<Utilisateur> utilisateurs = utilisateurRepository.findAll(pageable);
//            logger.info("Retrieved {} users", utilisateurs.getTotalElements());
//            return utilisateurs.map(utilisateurMapper::toDto);
//        } catch (Exception e) {
//            logger.error("Error occurred while retrieving users", e);
//            throw new RuntimeException("Error occurred while retrieving users", e);
//        }
//    }
//
//
//    @Override
//    public void supprimerUtilisateur(Long id) {
//        try {
//            utilisateurRepository.deleteById(id);
//            logger.info("User deleted successfully with id: {}", id);
//        } catch (Exception e) {
//            logger.error("Error occurred while deleting user with id: " + id, e);
//            throw new RuntimeException("Error occurred while deleting user with id: " + id, e);
//        }
//    }
//
//    @Override
//    public UtilisateurDto recupererUtilisateurParId(Long id) {
//        try {
//            Utilisateur utilisateur = utilisateurRepository.findById(id)
//                    .orElseThrow(() -> new UtilisateurNotFoundException("User not found with id: " , id.toString()));
//            logger.info("user recuperer avec success: {}", utilisateur.getId());
//            return utilisateurMapper.toDto(utilisateur);
//        }catch (Exception e) {
//            logger.error("Error occurred while retrieving user with id: " + id, e);
//            throw new RuntimeException("Error occurred while retrieving user with id: " + id, e);
//        }
//    }

