package com.simplon.couleur;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouleurDto implements Serializable {
    @NotNull
    String id;
    @NotNull
    String nom;
    @NotNull
    String hex;
    @NotNull
    String red;
    @NotNull
    String green;
    @NotNull
    String blue;
}
