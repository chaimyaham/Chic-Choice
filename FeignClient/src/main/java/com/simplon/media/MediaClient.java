package com.simplon.media;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "MEDIA")
public interface MediaClient {

    @PostMapping
    public ResponseEntity<MediaDto> upload(@RequestParam("file") MultipartFile multipartFile);

    @GetMapping("/{id}")
    public ResponseEntity<MediaDto> getMediaById(@PathVariable Long id);

    @PutMapping("/{id}")
    public ResponseEntity<MediaDto> updateMedia(@RequestParam("file") MultipartFile multipartFile, @PathVariable Long id);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMediaById(@PathVariable Long id);

}