package org.chicchoice.vetementservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
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
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private String marque ;
    private Long mediaId;
    private Long userId;
    private Boolean favoris;
    private String couleurId;
    @ManyToMany(mappedBy = "vetements")
    private List<Ensemble> ensembles;
    public boolean isFavoris() {
        return favoris;
    }


}
