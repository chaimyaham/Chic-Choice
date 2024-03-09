package org.chicchoice.vetementservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.chicchoice.vetementservice.enums.Category;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vetement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note;
    private LocalDateTime date_d_ajout;
    private Category category;
    private String marque ;
    private Long mediaId;
    private Long userId;
    private Boolean favoris;
    @ManyToMany(mappedBy = "vetements")
    private List<Ensemble> ensembles;



}
