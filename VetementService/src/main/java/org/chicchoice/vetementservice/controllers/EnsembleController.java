package org.chicchoice.vetementservice.controllers;

import jakarta.validation.Valid;
import org.chicchoice.vetementservice.dtos.EnsembleDto;
import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.dtos.request.EnsembleRequestDto;
import org.chicchoice.vetementservice.dtos.response.EnsembleResponseDto;
import org.chicchoice.vetementservice.services.impl.EnsembleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ensembles")
public class EnsembleController {
    private final EnsembleService ensembleService;
    private static final Logger logger = LoggerFactory.getLogger(EnsembleController.class);

    public EnsembleController(EnsembleService ensembleService){
        this.ensembleService=ensembleService;
    }
//
//EnsembleDto ajouterUnVetementAUnEnsemble(Long vetementId, Long ensembleId);
//    EnsembleDto supprimerUnVetementDunEnsemble(Long vetementId, Long ensembleId);
//    void supprimerUnEnsemble(Long ensembleId);
//    void marquerUnEnsembleCommeFavori(Long ensembleId);
//    EnsembleDto creerEtAjouterUnEnsemble(EnsembleDto ensembleDto);
//    Page<EnsembleDto> getAllEnsembles(Pageable pageable);
//    Page<EnsembleDto> getEnsemblesByUserID(Long userId,Pageable pageable);
//    Page<EnsembleDto>getEnsemblesFavorisByUserId(Long userId,Pageable pageable);

    @PostMapping("/{ensembleId}/vetements/{vetementId}")
    public ResponseEntity<EnsembleResponseDto> ajouterVetementAEnsemble(
            @PathVariable Long ensembleId,
            @PathVariable Long vetementId
    ) {
        logger.info("ajouter un vetement a un ensemble  controller");
        EnsembleResponseDto ensembleDto = ensembleService.ajouterUnVetementAUnEnsemble(vetementId, ensembleId);
        return new ResponseEntity<>(ensembleDto, HttpStatus.OK);
    }
    @GetMapping("/utilisateur/{idUtilisateur}/ensembles")
    public ResponseEntity<Page<EnsembleResponseDto>> obtenirEnsemblesCreerParUser(
            @PathVariable Long idUtilisateur,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        logger.info("Obtenir tous les ensembles crees par un utilisateur - Controleur");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<EnsembleResponseDto> ensemblesPage = ensembleService.getEnsemblesByUserID(idUtilisateur,pageable);
        return new ResponseEntity<>(ensemblesPage, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<EnsembleResponseDto>> obtenirToutEnsembles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        logger.info("Obtenir tous les ensembles - Controleur");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<EnsembleResponseDto> ensembleDtoPage=ensembleService.getAllEnsembles(pageable);
        return new ResponseEntity<>(ensembleDtoPage, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EnsembleResponseDto> creerUnEnsemble(@RequestBody @Valid EnsembleRequestDto ensemble){
        logger.info("Creer Un ensemble - Controleur");
        EnsembleResponseDto ensembleDto=ensembleService.creerEtAjouterUnEnsemble(ensemble);
        return new ResponseEntity<>(ensembleDto, HttpStatus.OK);
    }



}
