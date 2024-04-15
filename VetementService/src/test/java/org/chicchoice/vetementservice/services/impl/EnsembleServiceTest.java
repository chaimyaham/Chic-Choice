package org.chicchoice.vetementservice.services.impl;

import org.chicchoice.vetementservice.dtos.request.EnsembleRequestDto;
import org.chicchoice.vetementservice.dtos.response.EnsembleResponseDto;
import org.chicchoice.vetementservice.entities.Ensemble;
import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.enums.Category;
import org.chicchoice.vetementservice.exeptions.ResourceNotFoundException;
import org.chicchoice.vetementservice.exeptions.ServiceException;
import org.chicchoice.vetementservice.exeptions.VetementAlreadyExistsException;
import org.chicchoice.vetementservice.mapper.EnsembleMapper;
import org.chicchoice.vetementservice.mapper.VetementMapper;
import org.chicchoice.vetementservice.repositories.EnsembleRepository;
import org.chicchoice.vetementservice.repositories.VetementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnsembleServiceTest {
    @Mock
    private EnsembleRepository ensembleRepository;

    @Mock
    private EnsembleMapper ensembleMapper;

    @Mock
    private VetementRepository vetementRepository;

    @Mock
    private VetementMapper vetementMapper;

    @InjectMocks
    private EnsembleService ensembleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testAjouterUnVetementAUnEnsemble() {
        Vetement vetement = new Vetement();
        vetement.setId(1L);
        vetement.setCategory(Category.HAUT);
        Ensemble ensemble = new Ensemble();
        ensemble.setId(1L);
        ensemble.setVetements(new ArrayList<>());
        when(vetementRepository.findById(1L)).thenReturn(Optional.of(vetement));
        when(ensembleRepository.findById(1L)).thenReturn(Optional.of(ensemble));
        EnsembleResponseDto responseDto = ensembleService.ajouterUnVetementAUnEnsemble(1L, 1L);
        assertTrue(ensemble.getVetements().contains(vetement));
    }
    @Test
    void testAjouterUnVetementAUnEnsemble_ThrowVetementAlreadyExistsException() {
        Vetement vetement = new Vetement();
        vetement.setId(1L);
        vetement.setCategory(Category.HAUT);
        Ensemble ensemble = new Ensemble();
        ensemble.setId(1L);
        ensemble.setVetements(List.of(vetement));
        when(vetementRepository.findById(1L)).thenReturn(Optional.of(vetement));
        when(ensembleRepository.findById(1L)).thenReturn(Optional.of(ensemble));
        assertThrows(VetementAlreadyExistsException.class, () -> ensembleService.ajouterUnVetementAUnEnsemble(1L, 1L));
    }
    @Test
    void testAjouterUnVetementAUnEnsemble_EnsembleOuVetementNonExistant_ThrowResourceNotFoundException() {
        when(vetementRepository.findById(1L)).thenReturn(Optional.empty());
        when(ensembleRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> ensembleService.ajouterUnVetementAUnEnsemble(1L, 1L));
    }


    @Test
    void testMarquerUnEnsembleCommeFavori_EnsembleExistant_VerifyEnsembleMarkedAsFavorite() {
        Ensemble ensemble = new Ensemble();
        ensemble.setId(1L);
        ensemble.setFavoris(false);
        when(ensembleRepository.findById(1L)).thenReturn(Optional.of(ensemble));
        ensembleService.marquerUnEnsembleCommeFavori(1L);
        assertTrue(ensemble.isFavoris());
    }
    @Test
    void testMarquerUnEnsembleCommeFavori_EnsembleInexistant_ThrowResourceNotFoundException() {
        when(ensembleRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> ensembleService.marquerUnEnsembleCommeFavori(1L));
    }

    @Test
    public void testCreerEtAjouterUnEnsembleSuccess() {

        EnsembleRequestDto ensembleRequestDto = new EnsembleRequestDto();
        Ensemble ensemble = new Ensemble();
        Ensemble nouvelEnsemble = new Ensemble();
        EnsembleResponseDto ensembleResponseDto = new EnsembleResponseDto();
        when(ensembleMapper.toEntity(ensembleRequestDto)).thenReturn(ensemble);
        when(ensembleRepository.save(ensemble)).thenReturn(nouvelEnsemble);
        when(ensembleMapper.toDto1(nouvelEnsemble)).thenReturn(ensembleResponseDto);
        EnsembleResponseDto result = ensembleService.creerEtAjouterUnEnsemble(ensembleRequestDto);
        assertEquals(ensembleResponseDto, result);
        verify(ensembleMapper).toEntity(ensembleRequestDto);
        verify(ensembleRepository).save(ensemble);
        verify(ensembleMapper).toDto1(nouvelEnsemble);
    }

    @Test
    void testCreerEtAjouterUnEnsemble_Failure() {
        EnsembleRequestDto ensembleRequestDto = new EnsembleRequestDto();
        ensembleRequestDto.setNomDeLEnsemble("Nouvel ensemble");
        when(ensembleMapper.toEntity(ensembleRequestDto)).thenThrow(new RuntimeException());
        assertThrows(ServiceException.class, () -> ensembleService.creerEtAjouterUnEnsemble(ensembleRequestDto));
    }
    @Test
    public void testGetAllEnsemblesSuccess() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Ensemble> ensembleList = new ArrayList<>();
        Page<Ensemble> ensemblePage = new PageImpl<>(ensembleList);
        List<EnsembleResponseDto> ensembleResponseDtoList = new ArrayList<>();
        Page<EnsembleResponseDto> expectedPage = new PageImpl<>(ensembleResponseDtoList);
        when(ensembleRepository.findAll(pageable)).thenReturn(ensemblePage);
        when(ensembleMapper.toDto1(any(Ensemble.class))).thenReturn(new EnsembleResponseDto());
        Page<EnsembleResponseDto> result = ensembleService.getAllEnsembles(pageable);
        assertEquals(expectedPage, result);
        verify(ensembleRepository).findAll(pageable);
        verify(ensembleMapper, times(ensembleList.size())).toDto1(any(Ensemble.class));
    }
    @Test
    public void testGetAllEnsemblesGeneralException() {
        Pageable pageable = PageRequest.of(0, 10);
        when(ensembleRepository.findAll(pageable)).thenThrow(new RuntimeException());
        assertThrows(ServiceException.class, () -> ensembleService.getAllEnsembles(pageable));
        verify(ensembleRepository).findAll(pageable);
    }
    @Test
    public void testGetEnsemblesByUserIDSuccess() {
        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        List<Ensemble> ensembleList = new ArrayList<>();
        Page<Ensemble> ensemblePage = new PageImpl<>(ensembleList);
        List<EnsembleResponseDto> ensembleResponseDtoList = new ArrayList<>();
        Page<EnsembleResponseDto> expectedPage = new PageImpl<>(ensembleResponseDtoList);
        when(ensembleRepository.findAllByUtilisateurId(userId, pageable)).thenReturn(Optional.of(ensemblePage));
        when(ensembleMapper.toDto1(any(Ensemble.class))).thenReturn(new EnsembleResponseDto());
        Page<EnsembleResponseDto> result = ensembleService.getEnsemblesByUserID(userId, pageable);
        assertEquals(expectedPage, result);
        verify(ensembleRepository).findAllByUtilisateurId(userId, pageable);
        verify(ensembleMapper, times(ensembleList.size())).toDto1(any(Ensemble.class));
    }

    @Test
    public void testGetEnsemblesByUserIDEmptyResult() {

        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        when(ensembleRepository.findAllByUtilisateurId(userId, pageable)).thenReturn(Optional.empty());
        Page<EnsembleResponseDto> result = ensembleService.getEnsemblesByUserID(userId, pageable);
        assertTrue(result.isEmpty());
        verify(ensembleRepository).findAllByUtilisateurId(userId, pageable);
    }


    @Test
    public void testGetEnsemblesByUserIDGeneralException() {
        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        when(ensembleRepository.findAllByUtilisateurId(userId, pageable)).thenThrow(new RuntimeException());
        assertThrows(ServiceException.class, () -> ensembleService.getEnsemblesByUserID(userId, pageable));
        verify(ensembleRepository).findAllByUtilisateurId(userId, pageable);
    }



}