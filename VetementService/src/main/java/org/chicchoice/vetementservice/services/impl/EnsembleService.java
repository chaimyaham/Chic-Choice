package org.chicchoice.vetementservice.services.impl;

import org.chicchoice.vetementservice.dtos.EnsembleDto;
import org.chicchoice.vetementservice.dtos.request.EnsembleRequestDto;
import org.chicchoice.vetementservice.dtos.response.EnsembleResponseDto;
import org.chicchoice.vetementservice.entities.Ensemble;
import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.exeptions.ResourceNotFoundException;
import org.chicchoice.vetementservice.exeptions.ServiceException;
import org.chicchoice.vetementservice.exeptions.VetementAlreadyExistsException;
import org.chicchoice.vetementservice.mapper.EnsembleMapper;
import org.chicchoice.vetementservice.repositories.EnsembleRepository;
import org.chicchoice.vetementservice.repositories.VetementRepository;
import org.chicchoice.vetementservice.services.IEnsembleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EnsembleService implements IEnsembleService {
    private final EnsembleRepository ensembleRepository;
    private final EnsembleMapper ensembleMapper;
    private final VetementRepository vetementRepository;
    private static final Logger logger = LoggerFactory.getLogger(EnsembleService.class);


    @Autowired
    public EnsembleService(EnsembleRepository ensembleRepository,EnsembleMapper ensembleMapper,VetementRepository vetementRepository){
        this.ensembleMapper=ensembleMapper;
        this.ensembleRepository=ensembleRepository;
        this.vetementRepository=vetementRepository;
    }
    @Override
    public EnsembleResponseDto ajouterUnVetementAUnEnsemble(Long vetementId, Long ensembleId) {
        try {

            Optional<Vetement> existingVetement = vetementRepository.findById(vetementId);
            Optional<Ensemble> existingEnsemble = ensembleRepository.findById(ensembleId);
            //verifier si l id de l'ensemble et du vetement deja exist
            if (existingVetement.isEmpty() || existingEnsemble.isEmpty()) {
                logger.error("Vetement ou ensemble n existe pas avec cet ID");
                throw new ResourceNotFoundException("vetement ou ensemble", " avec cet ID n existe pas"," v-ID"+ vetementId.toString()+" - E-id " + ensembleId.toString());
            } else {
                //verifier si dans cet ensemble il exist deja cet article
                Ensemble ensemble = existingEnsemble.get();
                boolean vetementExistDejaDansEnsemble = ensemble.getVetements().stream()
                        .anyMatch(v -> v.getId().equals(vetementId));

                if (vetementExistDejaDansEnsemble) {
                    throw new VetementAlreadyExistsException("Le vetement choisi existe deja dans cet ensemble");
                } else {
                    //verifier si il exist deja un vetement de la meme category
                    ensemble.getVetements().removeIf(v -> v.getCategory().equals(existingVetement.get().getCategory()));
                    ensemble.getVetements().add(existingVetement.get());
                    Ensemble ensembleModifie = ensembleRepository.saveAndFlush(ensemble);
                    logger.info("vetement ajouter  avec succes a cet ensemble: {}", ensembleId);
                    return ensembleMapper.toDto1(ensembleModifie);
                }
            }

        } catch (Exception e) {
            logger.error("Erreur rencontree lors de l ajout de cet article a cet ensemble: {}", e.getMessage());
            throw new ServiceException("Ensemble", "Une erreur s est produite lors de l ajout de cet article a cet ensemble.", e);
        }
    }


    @Override
    public EnsembleResponseDto supprimerUnVetementDunEnsemble(Long vetementId, Long ensembleId) {
        //verifier si l'ensemble avec cet id exist
        try{
            Optional<Ensemble> existingEnsemble=ensembleRepository.findById(ensembleId);
            if(existingEnsemble.isEmpty()){
                logger.error("Ensemble n existe pas avec cet ID: {}",ensembleId);
                throw new ResourceNotFoundException("Ensemble", " avec cet ID n existe pas", ensembleId.toString());
            }
            Ensemble ensemble =existingEnsemble.get();
            //verifier si l'article  exist deja avant de le supprimer
            boolean vetementExistDansEnsemble = ensemble.getVetements().stream().anyMatch(v->v.getId().equals(vetementId));
            if(vetementExistDansEnsemble){
                //supprimer id du vetement de la liste des vetement de l 'ensemble
                ensemble.getVetements().removeIf(v->v.getId().equals(vetementId));
                Ensemble modifiedEnsemble = ensembleRepository.saveAndFlush(ensemble);
                logger.info("vetement supprimer avec succes de cet ensemble: {}", ensembleId);
                return ensembleMapper.toDto1(modifiedEnsemble);
            } else {
                logger.error("L article avec cet ID n existe pas dans cet ensemble : {} " , vetementId);
                throw new ResourceNotFoundException("Article", "avec cet ID n'existe pas dans cet ensemble", vetementId.toString());
            }
        }catch(Exception e){
            logger.error("Erreur rencontrer lors de la suppression  de cet article de cet ensemble: {}", e.getMessage());
            throw new ServiceException("Ensemble", "Une erreur s est produite lors de la suppression  de cet article de cet ensemble.", e);
        }

    }

    @Override
    public void supprimerUnEnsemble(Long ensembleId) {

        try{
            //verifier si l'ensemble exist deja
            Optional<Ensemble> existingEnsemble=ensembleRepository.findById(ensembleId);
            if(existingEnsemble.isEmpty()) {
                logger.error("Ensemble n existe pas avec cet ID: {}", ensembleId);
                throw new ResourceNotFoundException("Ensemble", " avec cet ID n existe pas", ensembleId.toString());
            }
            //supprimer l'ensemble
            ensembleRepository.deleteById(ensembleId);
            logger.info("Ensemble avec cet id est supprimer avec succes: {}", ensembleId);

        }catch(Exception e){
            logger.error("Erreur rencontrer lors de la suppression  de cet ensemble {}", e.getMessage());
            throw new ServiceException("Ensemble", "Une erreur s est produite lors de la suppression  de cet ensemble.", e);
        }

    }

    @Override
    public void marquerUnEnsembleCommeFavori(Long ensembleId) {
        try {
            // Verifier si l'ensemble existe
            ensembleRepository.findById(ensembleId).ifPresentOrElse(ensemble -> {
                if (!ensemble.isFavoris()) {
                    ensemble.setFavoris(true);
                    // Enregistrer les modifications
                    ensembleRepository.save(ensemble);
                    logger.info("ensemble marque comme favoris  : {}", ensembleId);
                } else {
                    logger.warn("ensemble marque comme favoris: {}", ensembleId);
                }
            }, () -> {
                logger.error("Ensemble non trouvé avec cet ID : {}", ensembleId);
                throw new ResourceNotFoundException("Ensemble", "avec cet ID n'existe pas", ensembleId.toString());
            });
        } catch (Exception e) {
            logger.error("Erreur lors du marquage de l ensemble comme favori : {}", e.getMessage());
            throw new ServiceException("Ensemble", "Une erreur est produite lors du marquage de l ensemble comme favori.", e);
        }
    }


    @Override
    public EnsembleResponseDto creerEtAjouterUnEnsemble(EnsembleRequestDto ensembleRequestDto) {
        try {
            Ensemble ensemble = ensembleMapper.toEntity(ensembleRequestDto);
            ensemble.setCreatedAt(LocalDateTime.now());
            Ensemble nouvelEnsemble = ensembleRepository.save(ensemble);
            logger.info("ensemble ajouter avec succes");
            return  ensembleMapper.toDto1(nouvelEnsemble);
        } catch (DataIntegrityViolationException e) {
            logger.error("Erreur lors de la creation et de l ajout de l ensemble : violation de contrainte d'integrite des donnees", e);
            throw new ServiceException("Ensemble", "Une erreur de violation de contrainte d'integrite des donnees est produite lors de la creation et de l ajout de l ensemble.", e);
        } catch (Exception e) {
            logger.error("Erreur lors de la creation et de l ajout de l ensemble", e);
            throw new ServiceException("Ensemble", "Une erreur s'est produite lors de la creation et de l'ajout de l'ensemble.", e);
        }
    }


    @Override
    public Page<EnsembleResponseDto> getAllEnsembles(Pageable pageable) {
        try{
            Page<Ensemble> ensembles = ensembleRepository.findAll(pageable);
            logger.info("list des ensembles recuperer avec succees");
            return  ensembles.map(ensembleMapper::toDto1);
        }catch(Exception e){
            logger.error("Erreur lors de la recuperaion de la liste des ensemble", e);
            throw new ServiceException("Ensemble", "Une erreur s'est produite lors de la recuperaion de la liste des ensembles.", e);
        }
    }

    @Override
    public Page<EnsembleResponseDto> getEnsemblesByUserID(Long userId,Pageable pageable) {
     try{
         //todo check if a user with that id already exist

         Optional<Page<Ensemble>> ensemblesCreerParUser=ensembleRepository.findAllByUtilisateurId(userId,pageable);
         logger.info("recuperation des ensembles avec userID");
         return ensemblesCreerParUser.map(ensembles -> ensembles.map(ensembleMapper::toDto1)).orElseGet(Page::empty);
     }catch (Exception e){
         logger.error("Erreur lors de la recuperaion de la liste des ensemble", e);
         throw new ServiceException("Ensemble", "Une erreur s'est produite lors de la recuperaion de la liste des ensembles.", e);
     }

     }

    @Override
    public Page<EnsembleResponseDto> getEnsemblesFavorisByUserId(Long userId, Pageable pageable) {
        //todo check if user with that id exist first
        try{
            Optional<Page<Ensemble>> ensemblesFavorisByUser=ensembleRepository.findAllByUtilisateurIdAndFavoris(userId,true,pageable);
            logger.info("recuperation des ensembles avec userID");
            return ensemblesFavorisByUser.map(ensembles->ensembles.map(ensembleMapper::toDto1)).orElseGet(Page::empty);
        }catch (Exception e){
            logger.error("Erreur lors de la recuperaion de la liste des ensemble", e);
            throw new ServiceException("Ensemble", "Une erreur s'est produite lors de la recuperaion de la liste des ensembles.", e);
        }

    }

}
