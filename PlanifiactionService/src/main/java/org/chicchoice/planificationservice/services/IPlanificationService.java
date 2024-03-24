package org.chicchoice.planificationservice.services;

import org.chicchoice.planificationservice.entities.Planification;

import java.time.LocalDate;
import java.util.List;

public interface IPlanificationService {
    List<Planification> getAllPlanififcationByUserId(Long userId);
    Planification ajouterPlanification(Planification planififcation);
    void supprimerPlanification(Long id);
    Planification ajouterUnEnsembleAUnePlanification(Long idPlanfifcation,Long idEnsemble);
    Planification  updatePlanification(Planification planification,Long id);
    void supprimerEnsembleDePlanification(Long idPlanification, Long idEnsemble);
    Planification getPlanificationByDateAndUtilisateurId(LocalDate date, Long userId);
    void supprimerPlanificationsByUtilisateurId(Long userId);
}
