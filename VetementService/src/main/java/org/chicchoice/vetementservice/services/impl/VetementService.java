package org.chicchoice.vetementservice.services.impl;

import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.enums.Category;
import org.chicchoice.vetementservice.exeptions.ServiceException;
import org.chicchoice.vetementservice.mapper.VetementMapper;
import org.chicchoice.vetementservice.repositories.VetementRepository;
import org.chicchoice.vetementservice.services.IVetementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetementService implements IVetementService {
    private final VetementRepository vetementRepository;
    private final VetementMapper vetementMapper;

    @Autowired
    public VetementService(VetementRepository vetementRepository,VetementMapper vetementMapper){
        this.vetementRepository=vetementRepository;
        this.vetementMapper=vetementMapper;
    }

    @Override
    public List<VetementDto> getAllVetements() {
        try{
            List<Vetement> vetements = vetementRepository.findAll();
            return vetements.stream().map(vetementMapper::toDTO).collect(Collectors.toList());
        }catch(Exception e){
            throw new ServiceException("Vetement","Une erreur s'est produite lors de la récupération de tous les vêtements.", e);
        }
    }


    @Override
    public List<VetementDto> getAllByUserId(Long userId) {
        return null;
    }

    @Override
    public VetementDto createVetement(VetementDto vetementDto) {
        return null;
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
