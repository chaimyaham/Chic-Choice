package org.chicchoice.utilisateurservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.chicchoice.utilisateurservice.enums.Sexe;
import org.chicchoice.utilisateurservice.enums.UtilisateurRole;

@Data
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private  String motDePasse;
    private String nom;
    private String prenom;
    private Sexe sexe;
    private UtilisateurRole role;
    private String localisation ;

    @Column(length = 1000)
    private String preferencesStyle;


}