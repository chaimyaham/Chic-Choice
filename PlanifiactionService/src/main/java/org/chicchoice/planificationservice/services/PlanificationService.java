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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
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
            throw new ResourceNotFoundException("planification","planification with that id n'exist pas",id.toString());
        }

    }

    @Override
    public PlanificationDto ajouterUnEnsembleAUnePlanification(Long idPlanfifcation, Long idEnsemble) {

           Optional<Planification> optionalPlanification = planificationRepository.findById(idPlanfifcation);
           if (optionalPlanification.isEmpty()) {
               logger.error("La planification avec cet identifiant n'existe pas : {}", idPlanfifcation);
               throw new ResourceNotFoundException("Planification", "La planification avec cet identifiant n'existe pas", idPlanfifcation.toString());
           }
//       verifier si l ensemble with that id exist on the planfication and also on the ensembles list
            ResponseEntity<EnsembleResponseDto> existingEnsemble=ensembleClient.getEnsembleById(idEnsemble);
           if (existingEnsemble.getStatusCode() != HttpStatus.OK) {
               logger.error("L'ensemble avec cet identifiant n'existe pas : {}", idEnsemble);
               throw new ResourceNotFoundException("Ensemble", "L'ensemble avec cet identifiant n'existe pas", idEnsemble.toString());
           }
           Planification planification = optionalPlanification.get();
           List<Long> ensembleIds = planification.getEnsemblesIds();
           if (ensembleIds.contains(idEnsemble)) {
               logger.error("L'ensemble avec cet id: {}  deja ajouter dans la planififcation", idEnsemble);
               throw new IllegalArgumentException("L'ensemble avec cet id: deja ajouter dans la planififcation");
           }
           ensembleIds.add(idEnsemble);
           planification.setEnsemblesIds(ensembleIds);
           Planification planififcationModifier=planificationRepository.save(planification);
           logger.info("ensemble ajouter avec succes a cet planification");
           return planificationMapper.toDto(planififcationModifier);

    }

    @Override
    public PlanificationDto updatePlanification(PlanificationDto planificationDto, Long id) {
        Optional<Planification> optionalPlanification = planificationRepository.findById(id);
        if (optionalPlanification.isEmpty()) {
            logger.error("Planification avec cet id {} n'existe pas", id);
            throw new ResourceNotFoundException("Planification", "Planification avec cet id {} n'existe pas", id.toString());
        }
        Planification existingPlanification = optionalPlanification.get();
        existingPlanification.setDateDebut(planificationDto.getDateDebut());
        existingPlanification.setDateFin(planificationDto.getDateFin());
        existingPlanification.setDescription(planificationDto.getDescription());
        existingPlanification.setMeteoId(planificationDto.getMeteoId());
        Planification savedPlanification = planificationRepository.save(existingPlanification);
        logger.info("Planification avec l'identifiant {} mise à jour avec succes", id);
        return planificationMapper.toDto(savedPlanification);
    }

    @Override
    public void supprimerEnsembleDePlanification(Long idPlanification, Long idEnsemble) {
        Optional<Planification> optionalPlanification = planificationRepository.findById(idPlanification);
        if (optionalPlanification.isEmpty()) {
            logger.error("La planification avec l'identifiant {} n'existe pas", idPlanification);
            throw new ResourceNotFoundException("Planification", "La planification avec cet identifiant n'existe pas", idPlanification.toString());
        }
        Planification planification = optionalPlanification.get();
        List<Long> ensembleIds = planification.getEnsemblesIds();
        if (!ensembleIds.contains(idEnsemble)) {
            logger.error("L'ensemble avec l'identifiant {} n'exist pas dans cette planification {}", idEnsemble, idPlanification);
            throw new ResourceNotFoundException("Ensemble", "L'ensemble avec l'identifiant {} n'exist pas dans cette planification", idEnsemble.toString());
        }
        ensembleIds.remove(idEnsemble);
        planification.setEnsemblesIds(ensembleIds);
        planificationRepository.save(planification);
        logger.info("Ensemble avec l'identifiant {} supprime de la planification avec l id avec success  {}", idEnsemble, idPlanification);

    }
    @Override
    public PlanificationDto getPlanificationByDateAndUtilisateurId(LocalDate date, Long userId) {
        try {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
            Optional<Planification> planificationOptional = planificationRepository.findFirstByDateDebutBetweenAndUtilisateurIdOrderByDateDebutAsc(startOfDay, endOfDay, userId);
            return planificationOptional.map(planificationMapper::toDto)
                    .orElseThrow(() -> new ResourceNotFoundException("Planification", "Aucune planification trouvée pour cette date et cet utilisateur", ""));
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la recherche des planifications par date et utilisateur", e);
            throw new ServiceException("Erreur lors de la recherche des planifications par date et utilisateur","planification service", e);
        }
    }

    @Override
    public void supprimerPlanificationsByUtilisateurId(Long userId) {
        try {
            List<Planification> planifications = planificationRepository.findAllByUtilisateurId(userId);
            if (!planifications.isEmpty()) {
                planificationRepository.deleteAllByUtilisateurId(userId);
                logger.info("Suppression de toutes les planifications de l'utilisateur avec l'id {}", userId);
            } else {
                logger.warn("Aucune planification trouve pour cet utilisateur {}", userId);
            }
        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la suppression des planifications de l'utilisateur avec l'identifiant {}", userId, e);
            throw new ServiceException("Erreur lors de la suppression des planifications de l'utilisateur", userId.toString(),e);
        }

    }

    @Override
    public Page<PlanificationDto> getAllPlanififcation(Pageable pageable) {
        try {
            Page<Planification> planifications = planificationRepository.findAll(pageable);
            logger.info("recuperation de la liste des planification avec  succes");
            return planifications.map(planificationMapper::toDto);
        }catch (Exception e){
            logger.error("une erreur est survenu lors de la recuperation de tout les planification ");
            throw new ResourceNotFoundException("Planification","une erreur est survenu lors de la recuperation de tout les planification","service");
        }
    }
}
