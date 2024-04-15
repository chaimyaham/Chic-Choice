package org.chicchoice.CouleurService.dto;

import javax.annotation.processing.Generated;
import org.chicchoice.CouleurService.entities.Couleur;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-14T03:12:33+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class CouleurMapperImpl implements CouleurMapper {

    @Override
    public Couleur toEntity(CouleurDto couleurDto) {
        if ( couleurDto == null ) {
            return null;
        }

        Couleur.CouleurBuilder couleur = Couleur.builder();

        couleur.id( couleurDto.getId() );
        couleur.nom( couleurDto.getNom() );
        couleur.hex( couleurDto.getHex() );
        couleur.red( couleurDto.getRed() );
        couleur.green( couleurDto.getGreen() );
        couleur.blue( couleurDto.getBlue() );

        return couleur.build();
    }

    @Override
    public CouleurDto toDto(Couleur couleur) {
        if ( couleur == null ) {
            return null;
        }

        CouleurDto.CouleurDtoBuilder couleurDto = CouleurDto.builder();

        couleurDto.id( couleur.getId() );
        couleurDto.nom( couleur.getNom() );
        couleurDto.hex( couleur.getHex() );
        couleurDto.red( couleur.getRed() );
        couleurDto.green( couleur.getGreen() );
        couleurDto.blue( couleur.getBlue() );

        return couleurDto.build();
    }

    @Override
    public Couleur partialUpdate(CouleurDto couleurDto, Couleur couleur) {
        if ( couleurDto == null ) {
            return couleur;
        }

        if ( couleurDto.getId() != null ) {
            couleur.setId( couleurDto.getId() );
        }
        if ( couleurDto.getNom() != null ) {
            couleur.setNom( couleurDto.getNom() );
        }
        if ( couleurDto.getHex() != null ) {
            couleur.setHex( couleurDto.getHex() );
        }
        if ( couleurDto.getRed() != null ) {
            couleur.setRed( couleurDto.getRed() );
        }
        if ( couleurDto.getGreen() != null ) {
            couleur.setGreen( couleurDto.getGreen() );
        }
        if ( couleurDto.getBlue() != null ) {
            couleur.setBlue( couleurDto.getBlue() );
        }

        return couleur;
    }
}
