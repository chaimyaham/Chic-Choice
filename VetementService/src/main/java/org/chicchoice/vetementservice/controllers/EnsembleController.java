package org.chicchoice.vetementservice.controllers;

import org.chicchoice.vetementservice.dtos.EnsembleDto;
import org.chicchoice.vetementservice.services.impl.EnsembleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ensembles")
public class EnsembleController {
    private final EnsembleService ensembleService;
    private static final Logger logger = LoggerFactory.getLogger(VetementController.class);

    public EnsembleController(EnsembleService ensembleService){
        this.ensembleService=ensembleService;
    }

    @PostMapping("/{ensembleId}/vetements/{vetementId}")
    public ResponseEntity<EnsembleDto> ajouterVetementAEnsemble(
            @PathVariable Long ensembleId,
            @PathVariable Long vetementId
    ) {
        logger.info("ajouter un vetement a un ensemble  controller");
        EnsembleDto ensembleDto = ensembleService.ajouterUnVetementAUnEnsemble(vetementId, ensembleId);
        return ResponseEntity.ok(ensembleDto);
    }

}
