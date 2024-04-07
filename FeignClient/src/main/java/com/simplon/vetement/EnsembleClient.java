package com.simplon.vetement;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FeignClient(name = "VETEMENT")
public interface EnsembleClient {

    @PostMapping("/api/v1/ensembles/{ensembleId}/vetements/{vetementId}")
    ResponseEntity<EnsembleResponseDto> ajouterVetementAEnsemble(
            @PathVariable("ensembleId") Long ensembleId,
            @PathVariable("vetementId") Long vetementId
    );

    @DeleteMapping("/api/v1/ensembles/{ensembleId}/vetements/{vetementId}")
    ResponseEntity<EnsembleResponseDto> supprimerVetementDunEnsemble(
            @PathVariable("ensembleId") Long ensembleId,
            @PathVariable("vetementId") Long vetementId
    );

    @GetMapping("/api/v1/ensembles/utilisateur/{idUtilisateur}/ensembles")
    ResponseEntity<Page<EnsembleResponseDto>> obtenirEnsemblesCreerParUser(
            @PathVariable("idUtilisateur") Long idUtilisateur,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

    @GetMapping("/api/v1/ensembles")
    ResponseEntity<Page<EnsembleResponseDto>> obtenirToutEnsembles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

    @PostMapping("/api/v1/ensembles")
    ResponseEntity<EnsembleResponseDto> creerUnEnsemble(@RequestBody @Valid EnsembleResponseDto ensemble);

    @GetMapping("/api/v1/ensembles/favoris/utilisateur/{userId}")
    ResponseEntity<Page<EnsembleResponseDto>> obtenirEnsemblesFavorisParUtilisateur(
            @PathVariable("userId") Long userId,
            Pageable pageable
    );

    @DeleteMapping("/api/v1/ensembles/{ensembleId}")
    ResponseEntity<Void> supprimerEnsemble(
            @PathVariable("ensembleId") Long ensembleId
    );

    @PostMapping("/api/v1/ensembles/{ensembleId}/favori")
    ResponseEntity<Void> marquerEnsembleCommeFavori(
            @PathVariable("ensembleId") Long ensembleId
    );

    @PutMapping("/api/v1/ensembles/{id}")
    ResponseEntity<EnsembleResponseDto> modifierEnsemble(
            @PathVariable("id") Long id,
            @RequestBody @Valid EnsembleResponseDto ensemble
    );
    @GetMapping("/api/v1/ensembles/{id}")
    ResponseEntity<EnsembleResponseDto> getEnsembleById(@PathVariable Long id);
}