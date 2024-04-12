package org.chicchoice.vetementservice.controllers;

import jakarta.validation.Valid;

import org.chicchoice.vetementservice.dtos.request.VetementRequestDto;
import org.chicchoice.vetementservice.dtos.response.VetementResponseDto;
import org.chicchoice.vetementservice.enums.Category;
import org.chicchoice.vetementservice.services.impl.VetementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vetements")
public class VetementController {
    private final VetementService vetementService;
    private static final Logger logger = LoggerFactory.getLogger(VetementController.class);

    public VetementController(VetementService vetementService){
        this.vetementService=vetementService;
    }


    @GetMapping
    public ResponseEntity<Page<VetementResponseDto>> getAllvetements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        logger.info("fetching all vetement by pagination");
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<VetementResponseDto> vetementDtoPage=vetementService.getAllVetements(pageable);
        return new ResponseEntity<>(vetementDtoPage, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<VetementResponseDto> ajouterVetement(@RequestBody @Valid VetementRequestDto vetement){
        logger.info("ajouter un nouveau vetement controller ");
        VetementResponseDto vetementDto=vetementService.createVetement(vetement);
        return new ResponseEntity<>(vetementDto,HttpStatus.OK);


    }
    @GetMapping("/{id}")
    public ResponseEntity<VetementResponseDto> obtenirVetementParId(
            @PathVariable Long id
    ){
        VetementResponseDto vetementDto = vetementService.getVetementById(id);
        logger.info("obtenir vetement par :{} controller ",id);
        return new ResponseEntity<>(vetementDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVetementParId(
            @PathVariable Long id
    ){
        vetementService.deleteVetementById(id);
        logger.info("deleting article avec id  :{} from controller ",id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/{id}/favori")
    public ResponseEntity<Void> marquerVetementCommeFavori(@PathVariable Long id){
        vetementService.marquerVetementCommeFavori(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categorie/{category}")
    public ResponseEntity<Page<VetementResponseDto>> getVetementsByCategory(
            @PathVariable Category category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<VetementResponseDto> vetementDtoPage = vetementService.getVetementsByCategory(category, pageable);
        return new ResponseEntity<>(vetementDtoPage, HttpStatus.OK);
    }
    @GetMapping("/categorie/{category}/utilisateur/{userId}")
    public ResponseEntity<Page<VetementResponseDto>> getVetementsByCategoryAndUser(
            @PathVariable Category category,
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<VetementResponseDto> vetementDtoPage = vetementService.getAllByCategoryAndUserId(userId, category, pageable);
        return new ResponseEntity<>(vetementDtoPage, HttpStatus.OK);
    }
    @GetMapping("/favoris/utilisateur/{userId}")
    public ResponseEntity<Page<VetementResponseDto>> getVetementsFavorisByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<VetementResponseDto> vetementDtoPage = vetementService.getVetementsFavorisByUserId(userId, pageable);
        return new ResponseEntity<>(vetementDtoPage, HttpStatus.OK);
    }

    @PutMapping("/{id}")
        public ResponseEntity<VetementResponseDto> modifierrVetement(
                @PathVariable Long id,
                @RequestBody @Valid VetementRequestDto vetement
    ){
            logger.info("modifier vetement controller ");
            VetementResponseDto vetementDto = vetementService.modifierVetement(id,vetement);
            return new ResponseEntity<>(vetementDto, HttpStatus.OK);

    }
    @GetMapping("/couleur/{couleurId}/utilisateur/{userId}")
    public ResponseEntity<Page<VetementResponseDto>> getVetementsByCouleurAndUser(
            @PathVariable String couleurId,
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<VetementResponseDto> vetementDtoPage = vetementService.getAllByColorIDandUserID(couleurId, userId, pageable);
        return new ResponseEntity<>(vetementDtoPage, HttpStatus.OK);
    }
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<Page<VetementResponseDto>> getAllVetementById(
            @PathVariable Long utilisateurId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<VetementResponseDto> vetementDtoPage = vetementService.getAllByUserId(utilisateurId,pageable);
        return new ResponseEntity<>(vetementDtoPage, HttpStatus.OK);
    }


}
