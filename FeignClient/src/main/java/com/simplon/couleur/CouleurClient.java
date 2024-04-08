package com.simplon.couleur;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "COULEUR")
public interface CouleurClient {

    @GetMapping("/api/v1/couleurs/{id}")
    ResponseEntity<CouleurDto> getColorById(@PathVariable("id") String id);
}
