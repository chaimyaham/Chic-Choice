package org.chicchoice.mediaservice.controller;

import lombok.RequiredArgsConstructor;
import org.chicchoice.mediaservice.dtos.MediaDto;
import org.chicchoice.mediaservice.service.ImageService;
import org.chicchoice.mediaservice.service.MediaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/media")
public class ImageController {

    private final ImageService imageService;
    private final MediaServiceImpl mediaService;

    @PostMapping
    public ResponseEntity<MediaDto> upload(@RequestParam("file") MultipartFile multipartFile) {
                String url=imageService.upload(multipartFile);
                MediaDto media=mediaService.saveMedia(url);
        return ResponseEntity.ok(media);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MediaDto> getMediaById(@PathVariable Long id) {
        MediaDto mediaDto = mediaService.getMedialById(id);
        return ResponseEntity.ok(mediaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaDto> updateMedia(@RequestParam("file") MultipartFile multipartFile,@PathVariable Long id) {
        String url=imageService.upload(multipartFile);
        MediaDto mediaDto = mediaService.updateMedia(id,url);
        return ResponseEntity.ok(mediaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMediaById(@PathVariable Long id) {
        mediaService.deleteMediaById(id);
        return ResponseEntity.noContent().build();
    }

}
