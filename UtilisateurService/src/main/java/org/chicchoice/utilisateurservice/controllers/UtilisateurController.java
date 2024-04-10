package org.chicchoice.utilisateurservice.controllers;


import org.chicchoice.utilisateurservice.dtos.LoginRequest;
import org.chicchoice.utilisateurservice.dtos.UtilisateurDto;
import org.chicchoice.utilisateurservice.services.KeycloakService;
import org.chicchoice.utilisateurservice.services.UtilisateurService;

import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
public class UtilisateurController {

    private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);
    private final UtilisateurService userService;
    private final KeycloakService keycloakService;

    public UtilisateurController(UtilisateurService userService,KeycloakService keycloakService){
        this.userService=userService;
        this.keycloakService=keycloakService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody UtilisateurDto signUpRequest){
        LOGGER.info("UserController | signUpUser a demarre. Role: {}, Email: {}, Nom: {}", signUpRequest.getRole(), signUpRequest.getEmail(), signUpRequest.getNom());
        String message = userService.signUpUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"" + message + "\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest request){

        LOGGER.info("UserController | login is started");

        AccessTokenResponse accessTokenResponse =keycloakService.loginWithKeycloak(request);
        if (accessTokenResponse == null){
            LOGGER.info("UserController | login | Http Status Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accessTokenResponse);
        }

        LOGGER.info("UserController | login | Http Status Ok");
        return ResponseEntity.ok(accessTokenResponse);
    }

    @GetMapping("{email}")
    public ResponseEntity<UtilisateurDto> getUserByEmail(@PathVariable("email") String email) {
        LOGGER.info("UserController | getUserByEmail a demarree");
        UtilisateurDto utilisateurDto = userService.recupererUtilisateurParEmail(email);
        return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<UtilisateurDto>> getAllUsers( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        LOGGER.info("UserController | getAllUsers a demarree");
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<UtilisateurDto> utilisateurs = userService.recupererToutsLesUtilisateur(pageable);
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

}
