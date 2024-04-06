package org.chicchoice.CouleurService.entities;


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
public class Couleur {
    @Id
    private String id;
    private String nom;
    private String hex;
    private String red;
    private String green;
    private String blue;
}
