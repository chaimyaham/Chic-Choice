package org.chicchoice.vetementservice.services;

import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.dtos.request.VetementRequestDto;
import org.chicchoice.vetementservice.dtos.response.VetementResponseDto;
import org.chicchoice.vetementservice.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVetementService {
    Page<VetementResponseDto> getAllVetements(Pageable pageable) ;
    Page<VetementResponseDto> getAllByUserId(Long userId,Pageable pageable);
    VetementResponseDto createVetement(VetementRequestDto vetementRequestDto);
    VetementResponseDto getVetementById(Long id);
    void deleteVetementById(Long id);
    void marquerVetementCommeFavori(Long vetementId);
    Page<VetementResponseDto> getVetementsByCategory(Category category,Pageable pageable);
    Page<VetementResponseDto> getVetementsFavorisByUserId(Long userId,Pageable pageable);
    Page<VetementResponseDto> getAllByCategoryAndUserId(Long userId,Category category,Pageable pageable);
    //todo ajouter fonction getallbycolor and userid
    //todo the ensemble that it will be created should be with the same user id as vetement

    VetementResponseDto modifierVetement(Long id,VetementRequestDto vetementRequestDto);

}
