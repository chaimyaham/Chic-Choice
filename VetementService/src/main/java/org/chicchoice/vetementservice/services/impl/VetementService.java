package org.chicchoice.vetementservice.services.impl;

import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.enums.Category;
import org.chicchoice.vetementservice.services.IVetementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetementService implements IVetementService {
    @Override
    public List<VetementDto> getAllVetements() {
        return null;
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
