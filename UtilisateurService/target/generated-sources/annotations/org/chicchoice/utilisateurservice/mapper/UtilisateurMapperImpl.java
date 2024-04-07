package org.chicchoice.utilisateurservice.mapper;

import javax.annotation.processing.Generated;
import org.chicchoice.utilisateurservice.dtos.UtilisateurDto;
import org.chicchoice.utilisateurservice.entities.Utilisateur;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-06T23:04:25+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class UtilisateurMapperImpl implements UtilisateurMapper {

    @Override
    public Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if ( utilisateurDto == null ) {
            return null;
        }

        Utilisateur.UtilisateurBuilder utilisateur = Utilisateur.builder();

        utilisateur.id( utilisateurDto.getId() );
        utilisateur.email( utilisateurDto.getEmail() );
        utilisateur.password( utilisateurDto.getPassword() );
        utilisateur.nom( utilisateurDto.getNom() );
        utilisateur.prenom( utilisateurDto.getPrenom() );
        utilisateur.username( utilisateurDto.getUsername() );
        utilisateur.sexe( utilisateurDto.getSexe() );
        utilisateur.role( utilisateurDto.getRole() );
        utilisateur.ville( utilisateurDto.getVille() );
        utilisateur.pays( utilisateurDto.getPays() );
        utilisateur.preferencesStyle( utilisateurDto.getPreferencesStyle() );

        return utilisateur.build();
    }

    @Override
    public UtilisateurDto toDto(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        UtilisateurDto.UtilisateurDtoBuilder utilisateurDto = UtilisateurDto.builder();

        utilisateurDto.id( utilisateur.getId() );
        utilisateurDto.email( utilisateur.getEmail() );
        utilisateurDto.password( utilisateur.getPassword() );
        utilisateurDto.nom( utilisateur.getNom() );
        utilisateurDto.prenom( utilisateur.getPrenom() );
        utilisateurDto.username( utilisateur.getUsername() );
        utilisateurDto.sexe( utilisateur.getSexe() );
        utilisateurDto.role( utilisateur.getRole() );
        utilisateurDto.ville( utilisateur.getVille() );
        utilisateurDto.pays( utilisateur.getPays() );
        utilisateurDto.preferencesStyle( utilisateur.getPreferencesStyle() );

        return utilisateurDto.build();
    }

    @Override
    public Utilisateur partialUpdate(UtilisateurDto utilisateurDto, Utilisateur utilisateur) {
        if ( utilisateurDto == null ) {
            return utilisateur;
        }

        if ( utilisateurDto.getId() != null ) {
            utilisateur.setId( utilisateurDto.getId() );
        }
        if ( utilisateurDto.getEmail() != null ) {
            utilisateur.setEmail( utilisateurDto.getEmail() );
        }
        if ( utilisateurDto.getPassword() != null ) {
            utilisateur.setPassword( utilisateurDto.getPassword() );
        }
        if ( utilisateurDto.getNom() != null ) {
            utilisateur.setNom( utilisateurDto.getNom() );
        }
        if ( utilisateurDto.getPrenom() != null ) {
            utilisateur.setPrenom( utilisateurDto.getPrenom() );
        }
        if ( utilisateurDto.getUsername() != null ) {
            utilisateur.setUsername( utilisateurDto.getUsername() );
        }
        if ( utilisateurDto.getSexe() != null ) {
            utilisateur.setSexe( utilisateurDto.getSexe() );
        }
        if ( utilisateurDto.getRole() != null ) {
            utilisateur.setRole( utilisateurDto.getRole() );
        }
        if ( utilisateurDto.getVille() != null ) {
            utilisateur.setVille( utilisateurDto.getVille() );
        }
        if ( utilisateurDto.getPays() != null ) {
            utilisateur.setPays( utilisateurDto.getPays() );
        }
        if ( utilisateurDto.getPreferencesStyle() != null ) {
            utilisateur.setPreferencesStyle( utilisateurDto.getPreferencesStyle() );
        }

        return utilisateur;
    }
}
