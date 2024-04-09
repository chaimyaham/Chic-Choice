package org.chicchoice.planificationservice.services;

import org.chicchoice.planificationservice.dtos.PlanificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IPlanificationService {
    Page<PlanificationDto> getAllPlanififcationByUserId(Long userId, Pageable pageable);
    PlanificationDto ajouterPlanification(PlanificationDto planificationDto);
    void supprimerPlanification(Long id);
    PlanificationDto ajouterUnEnsembleAUnePlanification(Long idPlanfifcation,Long idEnsemble);
    PlanificationDto  updatePlanification(PlanificationDto planificationDto,Long id);
    void supprimerEnsembleDePlanification(Long idPlanification, Long idEnsemble);
    PlanificationDto getPlanificationByDateAndUtilisateurId(LocalDate date, Long userId);
    void supprimerPlanificationsByUtilisateurId(Long userId);
    Page<PlanificationDto> getAllPlanififcation(Pageable pageable);
    List<PlanificationDto> getAllPlanficationThatContainsEnsemble(Long ensembleId);
}
