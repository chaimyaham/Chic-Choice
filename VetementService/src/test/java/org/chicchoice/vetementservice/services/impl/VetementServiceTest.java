package org.chicchoice.vetementservice.services.impl;

import com.simplon.couleur.CouleurClient;
import com.simplon.media.MediaClient;
import org.chicchoice.vetementservice.dtos.request.VetementRequestDto;
import org.chicchoice.vetementservice.dtos.response.VetementResponseDto;
import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.enums.Category;
import org.chicchoice.vetementservice.mapper.VetementMapper;
import org.chicchoice.vetementservice.repositories.VetementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetementServiceTest {

    @Mock
    private VetementRepository vetementRepository;

    @Mock
    private VetementMapper vetementMapper;

    @Mock
    private EnsembleService ensembleService;

    @Mock
    private MediaClient mediaClient;

    @Mock
    private CouleurClient couleurClient;

    @InjectMocks
    private VetementService vetementService;

    @Test
    void testSupprimerVetement() {
        Long vetementId = 1L;
        Vetement vetement = new Vetement();
        vetement.setId(vetementId);
        when(vetementRepository.findById(vetementId)).thenReturn(Optional.of(vetement));
        vetementService.deleteVetementById(vetementId);
        verify(ensembleService, times(1)).supprimerVetementDeTousEnsembles(vetement);
        verify(vetementRepository, times(1)).deleteById(vetementId);
    }



    @Test
    void testMarquerVetementCommeFavori() {
        Long vetementId = 1L;
        Vetement vetement = new Vetement();
        vetement.setId(vetementId);
        vetement.setFavoris(false);
        when(vetementRepository.findById(vetementId)).thenReturn(Optional.of(vetement));
        vetementService.marquerVetementCommeFavori(vetementId);
        verify(vetementRepository, times(1)).findById(vetementId);
        verify(vetementRepository, times(1)).save(any(Vetement.class));
    }
    @Test
    void testCreerVetement() {
        VetementRequestDto vetementRequestDto = new VetementRequestDto();
        vetementRequestDto.setCouleurId("1");
        vetementRequestDto.setMediaId(1L);
        when(couleurClient.getColorById("1")).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(mediaClient.getMediaById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        Vetement vetement = new Vetement();
        when(vetementMapper.toEntity(vetementRequestDto)).thenReturn(vetement);
        vetementService.createVetement(vetementRequestDto);
        verify(vetementRepository, times(1)).save(vetement);
    }


}
