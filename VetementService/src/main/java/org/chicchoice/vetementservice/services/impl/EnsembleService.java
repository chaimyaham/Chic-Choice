package org.chicchoice.vetementservice.services.impl;

import org.chicchoice.vetementservice.dtos.EnsembleDto;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnsembleService implements IEnsembleService {
    private final EnsembleRepository ensembleRepository;
    private final EnsembleMapper ensembleMapper;
    private final VetementRepository vetementRepository;
    private static final Logger logger = LoggerFactory.getLogger(VetementService.class);


    @Autowired
    public EnsembleService(EnsembleRepository ensembleRepository,EnsembleMapper ensembleMapper,VetementRepository vetementRepository){
        this.ensembleMapper=ensembleMapper;
        this.ensembleRepository=ensembleRepository;
        this.vetementRepository=vetementRepository;
    }
    @Override
    public EnsembleDto ajouterUnVetementAUnEnsemble(Long vetementId, Long ensembleId) {
        try {

            Optional<Vetement> existingVetement = vetementRepository.findById(vetementId);
            Optional<Ensemble> existingEnsemble = ensembleRepository.findById(ensembleId);
            //verifier si l id de l'ensemble et du vetement deja exist
            if (existingVetement.isEmpty() || existingEnsemble.isEmpty()) {
                logger.error("Vetement ou ensemble n existe pas avec cet ID");
                throw new ResourceNotFoundException("vetement ou ensemble", " avec cet ID n existe pas", vetementId.toString() + ensembleId.toString());
            } else {
                //vereifieer si dans cet eenseemble il exist deja ceet article
                Ensemble ensemble = existingEnsemble.get();
                boolean vetementExistDejaDansEnsemble = ensemble.getVetements().stream()
                        .anyMatch(v -> v.getId().equals(vetementId));

                if (vetementExistDejaDansEnsemble) {
                    throw new VetementAlreadyExistsException("Le vetement choisi existe deja dans cet ensemble");
                } else {
                    //verifier si il exist deja un vetement de la meme category
                    ensemble.getVetements().removeIf(v -> v.getCategory().equals(existingVetement.get().getCategory()));
                    ensemble.getVetements().add(existingVetement.get());
                    Ensemble ensembleModifie = ensembleRepository.save(ensemble);
                    return ensembleMapper.toDTO(ensembleModifie);
                }
            }

        } catch (Exception e) {
            logger.error("Erreur rencontree lors de l ajout de cet article a cet ensemble", e);
            throw new ServiceException("Ensemble", "Une erreur s est produite lors de l ajout de cet article a cet ensemble.", e);
        }
    }


    @Override
    public EnsembleDto supprimerUnVetementDunEnsemble(Long vetementId, Long ensembleId) {
        return null;
    }

    @Override
    public void supprimerUnEnsemble(Long ensembleId) {

    }

    @Override
    public void marquerUnEnsembleCommeFavori(Long ensembleId) {

    }

    @Override
    public EnsembleDto creerEtAjouterUnEnsemble(EnsembleDto ensembleDto) {
        return null;
    }

    @Override
    public List<EnsembleDto> getAllEnsembles() {
        return null;
    }

    @Override
    public List<EnsembleDto> getEnsemblesFavorisByUserID(Long userId) {
        return null;
    }
}
