package org.chicchoice.CouleurService.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Couleur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String codeRgb;
    private String codeHex;
    @OneToMany(mappedBy = "couleur", cascade = CascadeType.ALL)
    private List<ChangementCouleur> historiqueChangements;
    private void enregistrerChangement(String changement) {
        ChangementCouleur changementCouleur = new ChangementCouleur(changement, LocalDateTime.now());
        historiqueChangements.add(changementCouleur);
    }
}
