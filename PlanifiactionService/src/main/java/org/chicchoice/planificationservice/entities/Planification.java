package org.chicchoice.planificationservice.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.List;



@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Planification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private Long utilisateurId;

    @ElementCollection
    private List<Long> ensemblesIds;


}
