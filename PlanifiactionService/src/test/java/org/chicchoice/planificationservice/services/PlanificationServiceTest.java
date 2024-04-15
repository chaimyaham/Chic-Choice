package org.chicchoice.planificationservice.services;

import com.simplon.vetement.EnsembleClient;
import org.chicchoice.planificationservice.dtos.PlanificationDto;
import org.chicchoice.planificationservice.entities.Planification;
import org.chicchoice.planificationservice.exceptions.ResourceNotFoundException;
import org.chicchoice.planificationservice.mapper.PlanificationMapper;
import org.chicchoice.planificationservice.repository.PlanificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlanificationServiceTest {
    @Mock
    private PlanificationRepository planificationRepository;

    @Mock
    private PlanificationMapper planificationMapper;

    @Mock
    private EnsembleClient ensembleClient;

    @InjectMocks
    private PlanificationService planificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getAllPlanififcationByUserId_WithInvalidUserId_ThrowsResourceNotFoundException() {
        Long userId = 1L;
        Pageable pageable = mock(Pageable.class);
        when(planificationRepository.findAllByUtilisateurId(userId, pageable)).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> planificationService.getAllPlanififcationByUserId(userId, pageable));
        verify(planificationRepository).findAllByUtilisateurId(userId, pageable);
    }

    @Test
    void ajouterPlanification_WithValidPlanificationDto_ReturnsPlanificationDto() {
        PlanificationDto planificationDto = mock(PlanificationDto.class);
        Planification planification = mock(Planification.class);
        when(planificationMapper.toEntity(planificationDto)).thenReturn(planification);
        when(planificationRepository.save(planification)).thenReturn(planification);
        when(planificationMapper.toDto(planification)).thenReturn(planificationDto);
        PlanificationDto result = planificationService.ajouterPlanification(planificationDto);
        assertNotNull(result);
        assertEquals(planificationDto, result);
        verify(planificationMapper).toEntity(planificationDto);
        verify(planificationRepository).save(planification);
        verify(planificationMapper).toDto(planification);
    }
    @Test
    void supprimerPlanification_ExistingId_DeletesPlanification() {
        Long id = 1L;
        Planification planification = new Planification();
        when(planificationRepository.findById(id)).thenReturn(Optional.of(planification));
        planificationService.supprimerPlanification(id);
        verify(planificationRepository).findById(id);
        verify(planificationRepository).deleteById(id);
    }





}