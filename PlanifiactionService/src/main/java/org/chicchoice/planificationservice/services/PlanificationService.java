package org.chicchoice.planificationservice.services;

import com.simplon.vetement.EnsembleClient;
import com.simplon.vetement.EnsembleResponseDto;
import org.chicchoice.planificationservice.dtos.PlanificationDto;
import org.chicchoice.planificationservice.entities.Planification;
import org.chicchoice.planificationservice.exceptions.ResourceNotFoundException;
import org.chicchoice.planificationservice.exceptions.ServiceException;
import org.chicchoice.planificationservice.mapper.PlanificationMapper;
import org.chicchoice.planificationservice.repository.PlanificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PlanificationService implements IPlanificationService {

    private final PlanificationRepository planificationRepository;
    private final PlanificationMapper planificationMapper;
    private static final Logger logger = LoggerFactory.getLogger(PlanificationService.class);
    private final EnsembleClient ensembleClient;

    @Autowired
    public PlanificationService(PlanificationRepository planificationRepository,
                                PlanificationMapper planificationMapper,
                                EnsembleClient ensembleClient){
        this.planificationMapper=planificationMapper;
        this.planificationRepository=planificationRepository;
        this.ensembleClient=ensembleClient;
    }


    @Override
    public Page<PlanificationDto> getAllPlanififcationByUserId(Long userId, Pageable pageable) {
        try {
            Page<Planification> allExistingPalnificationByUserId = planificationRepository.findAllByUtilisateurId(userId,pageable);
           logger.info("recuperation de la liste des planification avec  succes");
           return allExistingPalnificationByUserId.map(planificationMapper::toDto);
        }catch (Exception e){
             logger.error("une erreur est survenu lors de la recuperation de tout les planification de cet userId {} ",userId);
             throw new ResourceNotFoundException("Planification","une erreur est survenu lors de la recuperation de tout les planification de cet userId",userId.toString());
        }

    }

    @Override
    public PlanificationDto ajouterPlanification(PlanificationDto planificationDto) {
        try{
            logger.info("planifcation service | ajouter planification");
            Planification planification=planificationMapper.toEntity(planificationDto);
            Planification savedPalnification=planificationRepository.save(planification);
            return planificationMapper.toDto(savedPalnification);

        }catch (Exception e){
            logger.error("Une erreur s'est produit lors de lajout de la nouvelle planification");
            throw new ServiceException("Planification","Une erreur s'est produit lors de lajout de la nouvelle planification",e);
        }

    }

    @Override
    public void supprimerPlanification(Long id) {
        try{
            Optional<Planification> planification = planificationRepository.findById(id);
            if (planification.isEmpty()){
                logger.error("planification with that id n'exist pas : {}",id);
                throw new ResourceNotFoundException("planification","planification with that id n'exist pas",id.toString());
            }
            planificationRepository.deleteById(id);
            logger.info("planification supprimer avec succes");

        }catch (Exception e){
            logger.error("Error encontrer lors de la suppression de cet plannifiication");
            throw new ServiceException("planification","Une erreur s'est produite lors de la suppression de cet planification", e);
        }

    }

    @Override
    public PlanificationDto ajouterUnEnsembleAUnePlanification(Long idPlanfifcation, Long idEnsemble) {
       try {
           Optional<Planification> optionalPlanification = planificationRepository.findById(idPlanfifcation);
           if (optionalPlanification.isEmpty()) {
               logger.error("La planification avec cet identifiant n'existe pas : {}", idPlanfifcation);
               throw new ResourceNotFoundException("Planification", "La planification avec cet identifiant n'existe pas", idPlanfifcation.toString());
           }

//        todo verifier si l ensemble with that id exist on the planfication and also on the ensembles


//        todo get the list of the ensembles id and add thet new id to the list

       }catch(Exception e){
           logger.error("Une erreur s'est produite lors de l'ajout d'un ensemble à une planification : ", e);
           throw new ServiceException("Planification", "Une erreur s'est produite lors de l'ajout d'un ensemble à une planification", e);
       }
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
