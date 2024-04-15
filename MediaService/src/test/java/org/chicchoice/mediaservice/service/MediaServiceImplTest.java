package org.chicchoice.mediaservice.service;

import org.chicchoice.mediaservice.dtos.MediaDto;
import org.chicchoice.mediaservice.dtos.MediaMapper;
import org.chicchoice.mediaservice.entities.Media;
import org.chicchoice.mediaservice.repository.MediaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MediaServiceImplTest {

    @Mock
    private MediaMapper mediaMapper;

    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;

    private Media media;
    private MediaDto mediaDto;

    @BeforeEach
    public void setUp() {
        media = new Media();
        mediaDto = new MediaDto();
    }

    @Test
    public void testSaveMedia() {
        String imageUrl = "http://example.com/image.jpg";
        when(mediaRepository.save(any(Media.class))).thenReturn(media);
        when(mediaMapper.toDto(media)).thenReturn(mediaDto);
        MediaDto result = mediaService.saveMedia(imageUrl);
        assertEquals(mediaDto, result);
        verify(mediaRepository, times(1)).save(any(Media.class));
    }

    @Test
    public void testGetMediaById() {
        Long id = 1L;
        when(mediaRepository.findById(id)).thenReturn(Optional.of(media));
        when(mediaMapper.toDto(media)).thenReturn(mediaDto);
        MediaDto result = mediaService.getMedialById(id);
        assertEquals(mediaDto, result);
        verify(mediaRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdateMedia() {
        Long id = 1L;
        String newUrl = "http://example.com/new-image.jpg";
        when(mediaRepository.findById(id)).thenReturn(Optional.of(media));
        when(mediaRepository.saveAndFlush(any(Media.class))).thenReturn(media);
        when(mediaMapper.toDto(media)).thenReturn(mediaDto);
        MediaDto result = mediaService.updateMedia(id, newUrl);
        assertEquals(mediaDto, result);
        verify(mediaRepository, times(1)).findById(id);
        verify(mediaRepository, times(1)).saveAndFlush(any(Media.class));
    }

    @Test
    public void testDeleteMediaById() {
        Long id = 1L;
        mediaService.deleteMediaById(id);
        verify(mediaRepository, times(1)).deleteById(id);
    }
}
