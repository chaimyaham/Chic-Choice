package org.chicchoice.CouleurService.services;

import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import org.chicchoice.CouleurService.dto.CouleurDto;
import org.chicchoice.CouleurService.dto.CouleurMapper;
import org.chicchoice.CouleurService.entities.Couleur;
import org.chicchoice.CouleurService.repository.CouleurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouleurServiceImplTest {

    @Mock
    private CouleurRepository couleurRepository;

    @Mock
    private CouleurMapper couleurMapper;

    @InjectMocks
    private CouleurServiceImpl couleurService;

    private Pageable pageable;
    private List<Couleur> couleurList;
    private Page<Couleur> couleurPage;

    @BeforeEach
    public void setUp() {
        pageable = PageRequest.of(0, 2);
        couleurList = Arrays.asList(new Couleur(), new Couleur());
        couleurPage = new PageImpl<>(couleurList, pageable, couleurList.size());
    }

    @Test
    public void testGetAll() {
        when(couleurRepository.findAll(pageable)).thenReturn(couleurPage);
        when(couleurMapper.toDto(couleurList.get(0))).thenReturn(new CouleurDto());
        when(couleurMapper.toDto(couleurList.get(1))).thenReturn(new CouleurDto());
        Page<CouleurDto> result = couleurService.getAll(pageable);
        assertEquals(2, result.getContent().size());
    }
    @Test
    public void testGetColorById() {
        String id = "testId";
        CouleurDto couleurDto=new CouleurDto();
        Couleur couleur =new Couleur();
        when(couleurRepository.findById(id)).thenReturn(Optional.of(couleur));
        when(couleurMapper.toDto(couleur)).thenReturn(couleurDto);

        CouleurDto result = couleurService.getColorById(id);

        assertEquals(couleurDto, result);
    }

    @Test
    public void testGetColorByIdNotFound() {
        String id = "testId";
        when(couleurRepository.findById(id)).thenReturn(Optional.empty());

        try {
            couleurService.getColorById(id);
        } catch (EntityNotFoundException e) {
            assertEquals("Couleur not found for id: " + id, e.getMessage());
        }
    }
}