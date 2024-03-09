package org.chicchoice.vetementservice.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ensemble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private LocalDateTime createdAt;
    private String nomDeLEnsemble;
    private Long utilisateurId;
    private Boolean favoris;
    @ManyToMany
    @JoinTable(
            name = "ensemble_vetement",
            joinColumns = @JoinColumn(name = "ensemble_id"),
            inverseJoinColumns = @JoinColumn(name = "vetement_id")
    )
    private List<Vetement> vetements;


}
