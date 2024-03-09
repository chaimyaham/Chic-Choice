package org.chicchoice.vetementservice.services;

import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.enums.Category;

import java.util.List;

public interface IVetementService {
    List<VetementDto> getAllVetements();
    List<VetementDto> getAllByUserId(Long userId);
    VetementDto createVetement(VetementDto vetementDto);
    VetementDto getVetementById(Long id);
    void deleteVetementById(Long id);
    void marquerVetementCommeFavori(Long vetementId);
    List<VetementDto> getVetementsByCategory(Category category);
    List<VetementDto> getVetementsFavorisByUserId(Long userId);

}
