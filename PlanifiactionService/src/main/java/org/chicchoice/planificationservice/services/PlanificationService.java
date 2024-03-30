package org.chicchoice.planificationservice.services;

import org.chicchoice.planificationservice.dtos.PlanificationDto;
import org.chicchoice.planificationservice.entities.Planification;
import org.chicchoice.planificationservice.mapper.PlanificationMapper;
import org.chicchoice.planificationservice.repository.PlanificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlanificationService implements IPlanificationService {

    private final PlanificationRepository planificationRepository;
    private final PlanificationMapper planificationMapper;
    private static final Logger logger = LoggerFactory.getLogger(PlanificationService.class);

    @Autowired
    public PlanificationService( PlanificationRepository planificationRepository,PlanificationMapper planificationMapper){
        this.planificationMapper=planificationMapper;
        this.planificationRepository=planificationRepository;
    }


    @Override
    public Page<PlanificationDto> getAllPlanififcationByUserId(Long userId, Pageable pageable) {
        try {
            Page<Planification> allExistingPalnificationByUserId = planificationRepository.findAllByUtilisateurId(userId,pageable);
           logger.info("recuperation de la liste des planification avec  succes");
           return allExistingPalnificationByUserId.map(planificationMapper::toDto);
        }catch (Exception e){
             logger.error("une erreur est survenu lors de la recuperation de tout les planification de cet userId {} ",userId);
        }
        return null;
    }

    @Override
    public PlanificationDto ajouterPlanification(PlanificationDto planificationDto) {
        return null;
    }

    @Override
    public void supprimerPlanification(Long id) {

    }

    @Override
    public PlanificationDto ajouterUnEnsembleAUnePlanification(Long idPlanfifcation, Long idEnsemble) {
        return null;
    }

    @Override
    public PlanificationDto updatePlanification(PlanificationDto planificationDto, Long id) {
        return null;
    }

    @Override
    public void supprimerEnsembleDePlanification(Long idPlanification, Long idEnsemble) {

    }

    @Override
    public PlanificationDto getPlanificationByDateAndUtilisateurId(LocalDate date, Long userId) {
        return null;
    }

    @Override
    public void supprimerPlanificationsByUtilisateurId(Long userId) {

    }
}
