package org.chicchoice.vetementservice.controllers;

import jakarta.validation.Valid;
import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.dtos.request.VetementRequestDto;
import org.chicchoice.vetementservice.dtos.response.VetementResponseDto;
import org.chicchoice.vetementservice.services.impl.VetementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vetements")
public class VetementController {
    private final VetementService vetementService;
    private static final Logger logger = LoggerFactory.getLogger(VetementController.class);

    @Autowired
    public VetementController(VetementService vetementService){
        this.vetementService=vetementService;
    }


    @GetMapping
    public ResponseEntity<Page<VetementResponseDto>> getAllvetements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        logger.info("fetching all vetement by pagination");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<VetementResponseDto> vetementDtoPage=vetementService.getAllVetements(pageable);
        return new ResponseEntity<>(vetementDtoPage, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<VetementResponseDto> ajouterVetement(@RequestBody @Valid VetementRequestDto vetement){
        logger.info("ajouter un nouveau vetement controller ");
        VetementResponseDto vetementDto=vetementService.createVetement(vetement);
        return new ResponseEntity<>(vetementDto,HttpStatus.OK);


    }

}
