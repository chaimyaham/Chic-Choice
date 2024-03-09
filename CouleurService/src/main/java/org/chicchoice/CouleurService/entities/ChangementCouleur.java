package org.chicchoice.CouleurService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChangementCouleur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String changement;
    private LocalDateTime dateChangement;

    @ManyToOne
    @JoinColumn(name = "couleur_nom")
    private Couleur couleur;

    public ChangementCouleur(String changement, LocalDateTime dateChangement) {
        this.changement = changement;
        this.dateChangement = dateChangement;
    }
}
