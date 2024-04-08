package org.chicchoice.planificationservice.controllers;

import org.chicchoice.planificationservice.services.PlanificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.chicchoice.planificationservice.dtos.PlanificationDto;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/planfications")
public class PlanificationController {
    private final PlanificationService planificationService;
    private static final Logger logger = LoggerFactory.getLogger(PlanificationController.class);

    @Autowired
    public PlanificationController(PlanificationService planificationService){
        this.planificationService=planificationService;
    }
    @PostMapping
    public ResponseEntity<PlanificationDto> ajouterPlanification(@RequestBody PlanificationDto planificationDto) {
        PlanificationDto savedPlanificationDto = planificationService.ajouterPlanification(planificationDto);
        return new ResponseEntity<>(savedPlanificationDto, HttpStatus.CREATED);
    }
    @PostMapping("/ajouter-ensemble-planification")
    public ResponseEntity<PlanificationDto> ajouterUnEnsembleAUnePlanification(
            @RequestParam Long idPlanification,
            @RequestParam Long idEnsemble
    ) {
        PlanificationDto planificationDto = planificationService.ajouterUnEnsembleAUnePlanification(idPlanification, idEnsemble);
        return new ResponseEntity<>(planificationDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PlanificationDto>> getAllPlanififcationByUserId(
            @RequestParam Long userId,
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue = "10")int size
    ) {
        Pageable pageable =  Pageable.ofSize(size).withPage(page);
        Page<PlanificationDto> planificationPage = planificationService.getAllPlanififcationByUserId(userId, pageable);
        return new ResponseEntity<>(planificationPage, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Page<PlanificationDto>> getAll(
            @RequestParam(defaultValue="0" )int page,
            @RequestParam(defaultValue = "10")int size
    ){
        logger.info("Obtenir tous les planifications");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<PlanificationDto> planficationPage = planificationService.getAllPlanififcation(pageable);
        return new ResponseEntity<>(planficationPage, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlanificationDto> updatePlanification(@RequestBody PlanificationDto planificationDto, @PathVariable Long id) {
        logger.info("update planification id :{} | Controller",id);
        PlanificationDto updatedPlanification = planificationService.updatePlanification(planificationDto, id);
        return new ResponseEntity<>(updatedPlanification,HttpStatus.OK);
    }

    @DeleteMapping("/{idPlanification}/ensembles/{idEnsemble}")
    public ResponseEntity<Void> supprimerEnsembleDePlanification(@PathVariable Long idPlanification, @PathVariable Long idEnsemble) {
        logger.info("supprimer ensemble de l id :{} de la planification id :{} | Controller",idEnsemble,idPlanification);
        planificationService.supprimerEnsembleDePlanification(idPlanification,idEnsemble);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPlanification(@PathVariable Long id) {
        logger.info("supprimer la planification avec cette id {} avec succes",id);
        planificationService.supprimerPlanification(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/utilisateur/{userId}")
    public ResponseEntity<Void> supprimerPalnificationsParUtilisateur(
            @PathVariable Long userId
    ){
        logger.info("supprimer toutes les planifications de cet utilisateur {} avec succes | Controller",userId);
        planificationService.supprimerPlanificationsByUtilisateurId(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/utilisateur/{userId}/date/{date}")
    public ResponseEntity<PlanificationDto> getPlanificationByDateAndUtilisateurId(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date, @PathVariable Long userId) {
            logger.info("Recherche des planifications pour l'utilisateur avec l'id {} pour la date {}", userId, date);
            PlanificationDto planificationDto = planificationService.getPlanificationByDateAndUtilisateurId(date, userId);
            return new ResponseEntity<>(planificationDto,HttpStatus.OK);

    }


}
