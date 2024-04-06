package org.chicchoice.CouleurService.services;

import jakarta.persistence.EntityNotFoundException;
import org.chicchoice.CouleurService.dto.CouleurDto;
import org.chicchoice.CouleurService.dto.CouleurMapper;
import org.chicchoice.CouleurService.entities.Couleur;
import org.chicchoice.CouleurService.repository.CouleurRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouleurServiceImpl implements CouleurService {
    private final CouleurRepository couleurRepository;
    private final CouleurMapper couleurMapper;
    private static final Logger logger = LoggerFactory.getLogger(CouleurServiceImpl.class);


    @Autowired
    public CouleurServiceImpl(CouleurMapper couleurMapper, CouleurRepository couleurRepository){
        this.couleurMapper=couleurMapper;
        this.couleurRepository=couleurRepository;
    }
    @Override
    public Page<CouleurDto> getAll(Pageable pageable) {
        try{

            Page<Couleur> colors = couleurRepository.findAll(pageable);
            logger.info("Couleur Service | List des couleur recupere avec succes");
            return colors.map(couleurMapper::toDto);

        }catch (Exception e){
            logger.error("Error encontre lors de la recuperation de la liste des couleurs");
            throw new ServiceException("Erreur  encontre lors de la recuperation de la liste des couleurs ");
        }
    }

    @Override
    public CouleurDto getColorById(String id) {
        Optional<Couleur> optionalCouleur = couleurRepository.findById(id);
        if (optionalCouleur.isPresent()) {
            logger.info("Couleur Service | couleur recuperer avec success");
            return couleurMapper.toDto(optionalCouleur.get());
        } else {
            logger.error("Error encontre lors de la recuperation de cet couleur");
            throw new EntityNotFoundException("Couleur not found for id: " + id);
        }

    }
}
