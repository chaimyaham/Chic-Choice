package org.chicchoice.utilisateurservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.chicchoice.utilisateurservice.enums.Sexe;
import org.chicchoice.utilisateurservice.enums.UtilisateurRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String username;
    @Enumerated(value = EnumType.STRING)
    private Sexe sexe;
    @Enumerated(value = EnumType.STRING)
    private UtilisateurRole role;
    private String ville ;
    private String pays ;

    @Column(length = 1000)
    private String preferencesStyle;


}