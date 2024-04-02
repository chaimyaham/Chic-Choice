package com.simplon.media;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "MEDIA")
public interface MediaClient {
//    requestPart instead of request param

    @PostMapping("/api/v1/media")
    public ResponseEntity<MediaDto> upload(@RequestParam("file") MultipartFile multipartFile);

    @GetMapping("/api/v1/media/{id}")
    public ResponseEntity<MediaDto> getMediaById(@PathVariable("id") Long id);

    @PutMapping("/api/v1/media/{id}")
    public ResponseEntity<MediaDto> updateMedia(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Long id);

    @DeleteMapping("/api/v1/media/{id}")
    public ResponseEntity<Void> deleteMediaById(@PathVariable("id") Long id);

}