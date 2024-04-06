package org.chicchoice.CouleurService.controller;

import org.chicchoice.CouleurService.dto.CouleurDto;
import org.chicchoice.CouleurService.services.CouleurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/couleurs")
public class CouleurController {
    private final CouleurService couleurService;
    private static final Logger logger = LoggerFactory.getLogger(CouleurController.class);

    public CouleurController(CouleurService couleurService){
        this.couleurService=couleurService;
    }

    @GetMapping
    public ResponseEntity<Page<CouleurDto>> getAllColors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        logger.info("Obtenir tous les couleurs | CouleurControleur");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<CouleurDto> colors = couleurService.getAll(pageable);
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouleurDto> getColorById(
            @PathVariable String id
    ){
        logger.info("Obtenir la couleur avec cet id {}| CouleurControleur",id);
        CouleurDto couleur = couleurService.getColorById(id);
        return new ResponseEntity<>(couleur,HttpStatus.OK);
    }
}
