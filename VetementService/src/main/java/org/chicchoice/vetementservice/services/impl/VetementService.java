package org.chicchoice.vetementservice.services.impl;

import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.enums.Category;
import org.chicchoice.vetementservice.exeptions.ServiceException;
import org.chicchoice.vetementservice.exeptions.VetementAlreadyExistsException;
import org.chicchoice.vetementservice.mapper.VetementMapper;
import org.chicchoice.vetementservice.repositories.VetementRepository;
import org.chicchoice.vetementservice.services.IVetementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VetementService implements IVetementService {
    private final VetementRepository vetementRepository;
    private final VetementMapper vetementMapper;
    private static final Logger logger = LoggerFactory.getLogger(VetementService.class);

    @Autowired
    public VetementService(VetementRepository vetementRepository,VetementMapper vetementMapper){
        this.vetementRepository=vetementRepository;
        this.vetementMapper=vetementMapper;
    }

    @Override
    public Page<VetementDto> getAllVetements(Pageable pageable) {
        try{
            Page<Vetement> vetements = vetementRepository.findAll(pageable);
            logger.info("List des vetements recupere avec succes");
            return vetements.map(vetementMapper::toDTO);
        }catch(Exception e){
            logger.error("Error encontre lors de la recuperation de la liste des vetement");
            throw new ServiceException("Vetement","Une erreur s'est produite lors de la récupération de tous les vêtements.", e);
        }
    }


    @Override
    public List<VetementDto> getAllByUserId(Long userId) {
        return null;
    }

    @Override
    public VetementDto createVetement(VetementDto vetementDto) {
        try{
            Optional<Vetement> vetement =vetementRepository.findByMediaId(vetementDto.getMediaId());
            if(vetement.isPresent()){
                logger.error("Vetement already exist");
                throw new VetementAlreadyExistsException("l'article with that id already exist");
            }
            Vetement article=vetementMapper.toEntity(vetementDto);
            Vetement savedVetement = vetementRepository.save(article);
            logger.info("Vetement ajouter avec success");
            return vetementMapper.toDTO(savedVetement);
        }catch(Exception e){
            throw new ServiceException("Vetement","Une erreur s'est produite lors de la creation de cet article.", e);
        }
    }

    @Override
    public VetementDto getVetementById(Long id) {
        return null;
    }

    @Override
    public void deleteVetementById(Long id) {

    }

    @Override
    public void marquerVetementCommeFavori(Long vetementId) {

    }

    @Override
    public List<VetementDto> getVetementsByCategory(Category category) {
        return null;
    }

    @Override
    public List<VetementDto> getVetementsFavorisByUserId(Long userId) {
        return null;
    }
}
