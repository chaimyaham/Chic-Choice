package org.chicchoice.vetementservice.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.chicchoice.vetementservice.dtos.request.EnsembleRequestDto;
import org.chicchoice.vetementservice.dtos.response.EnsembleResponseDto;
import org.chicchoice.vetementservice.entities.Ensemble;
import org.chicchoice.vetementservice.entities.Vetement;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T15:38:58+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class EnsembleMapperImpl implements EnsembleMapper {

    @Override
    public Ensemble toEntity(EnsembleRequestDto ensembleRequestDto) {
        if ( ensembleRequestDto == null ) {
            return null;
        }

        Ensemble.EnsembleBuilder ensemble = Ensemble.builder();

        ensemble.nomDeLEnsemble( ensembleRequestDto.getNomDeLEnsemble() );
        ensemble.utilisateurId( ensembleRequestDto.getUtilisateurId() );
        ensemble.favoris( ensembleRequestDto.getFavoris() );

        return ensemble.build();
    }

    @Override
    public EnsembleRequestDto toDto(Ensemble ensemble) {
        if ( ensemble == null ) {
            return null;
        }

        EnsembleRequestDto ensembleRequestDto = new EnsembleRequestDto();

        ensembleRequestDto.setNomDeLEnsemble( ensemble.getNomDeLEnsemble() );
        ensembleRequestDto.setUtilisateurId( ensemble.getUtilisateurId() );
        ensembleRequestDto.setFavoris( ensemble.getFavoris() );

        return ensembleRequestDto;
    }

    @Override
    public Ensemble partialUpdate(EnsembleRequestDto ensembleRequestDto, Ensemble ensemble) {
        if ( ensembleRequestDto == null ) {
            return ensemble;
        }

        if ( ensembleRequestDto.getNomDeLEnsemble() != null ) {
            ensemble.setNomDeLEnsemble( ensembleRequestDto.getNomDeLEnsemble() );
        }
        if ( ensembleRequestDto.getUtilisateurId() != null ) {
            ensemble.setUtilisateurId( ensembleRequestDto.getUtilisateurId() );
        }
        if ( ensembleRequestDto.getFavoris() != null ) {
            ensemble.setFavoris( ensembleRequestDto.getFavoris() );
        }

        return ensemble;
    }

    @Override
    public Ensemble toEntity(EnsembleResponseDto ensembleResponseDto) {
        if ( ensembleResponseDto == null ) {
            return null;
        }

        Ensemble.EnsembleBuilder ensemble = Ensemble.builder();

        ensemble.id( ensembleResponseDto.getId() );
        ensemble.createdAt( ensembleResponseDto.getCreatedAt() );
        ensemble.nomDeLEnsemble( ensembleResponseDto.getNomDeLEnsemble() );
        ensemble.utilisateurId( ensembleResponseDto.getUtilisateurId() );
        ensemble.favoris( ensembleResponseDto.getFavoris() );
        ensemble.vetements( vetementDtoListToVetementList( ensembleResponseDto.getVetements() ) );

        return ensemble.build();
    }

    @Override
    public EnsembleResponseDto toDto1(Ensemble ensemble) {
        if ( ensemble == null ) {
            return null;
        }

        EnsembleResponseDto ensembleResponseDto = new EnsembleResponseDto();

        ensembleResponseDto.setId( ensemble.getId() );
        ensembleResponseDto.setCreatedAt( ensemble.getCreatedAt() );
        ensembleResponseDto.setNomDeLEnsemble( ensemble.getNomDeLEnsemble() );
        ensembleResponseDto.setUtilisateurId( ensemble.getUtilisateurId() );
        ensembleResponseDto.setFavoris( ensemble.getFavoris() );
        ensembleResponseDto.setVetements( vetementListToVetementDtoList( ensemble.getVetements() ) );

        return ensembleResponseDto;
    }

    @Override
    public Ensemble partialUpdate(EnsembleResponseDto ensembleResponseDto, Ensemble ensemble) {
        if ( ensembleResponseDto == null ) {
            return ensemble;
        }

        if ( ensembleResponseDto.getId() != null ) {
            ensemble.setId( ensembleResponseDto.getId() );
        }
        if ( ensembleResponseDto.getCreatedAt() != null ) {
            ensemble.setCreatedAt( ensembleResponseDto.getCreatedAt() );
        }
        if ( ensembleResponseDto.getNomDeLEnsemble() != null ) {
            ensemble.setNomDeLEnsemble( ensembleResponseDto.getNomDeLEnsemble() );
        }
        if ( ensembleResponseDto.getUtilisateurId() != null ) {
            ensemble.setUtilisateurId( ensembleResponseDto.getUtilisateurId() );
        }
        if ( ensembleResponseDto.getFavoris() != null ) {
            ensemble.setFavoris( ensembleResponseDto.getFavoris() );
        }
        if ( ensemble.getVetements() != null ) {
            List<Vetement> list = vetementDtoListToVetementList( ensembleResponseDto.getVetements() );
            if ( list != null ) {
                ensemble.getVetements().clear();
                ensemble.getVetements().addAll( list );
            }
        }
        else {
            List<Vetement> list = vetementDtoListToVetementList( ensembleResponseDto.getVetements() );
            if ( list != null ) {
                ensemble.setVetements( list );
            }
        }

        return ensemble;
    }

    protected Vetement vetementDtoToVetement(EnsembleResponseDto.VetementDto vetementDto) {
        if ( vetementDto == null ) {
            return null;
        }

        Vetement.VetementBuilder vetement = Vetement.builder();

        vetement.id( vetementDto.getId() );
        vetement.note( vetementDto.getNote() );
        vetement.date_d_ajout( vetementDto.getDate_d_ajout() );
        vetement.category( vetementDto.getCategory() );
        vetement.marque( vetementDto.getMarque() );
        vetement.mediaId( vetementDto.getMediaId() );
        vetement.userId( vetementDto.getUserId() );
        vetement.favoris( vetementDto.getFavoris() );

        return vetement.build();
    }

    protected List<Vetement> vetementDtoListToVetementList(List<EnsembleResponseDto.VetementDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Vetement> list1 = new ArrayList<Vetement>( list.size() );
        for ( EnsembleResponseDto.VetementDto vetementDto : list ) {
            list1.add( vetementDtoToVetement( vetementDto ) );
        }

        return list1;
    }

    protected EnsembleResponseDto.VetementDto vetementToVetementDto(Vetement vetement) {
        if ( vetement == null ) {
            return null;
        }

        EnsembleResponseDto.VetementDto vetementDto = new EnsembleResponseDto.VetementDto();

        vetementDto.setId( vetement.getId() );
        vetementDto.setNote( vetement.getNote() );
        vetementDto.setDate_d_ajout( vetement.getDate_d_ajout() );
        vetementDto.setCategory( vetement.getCategory() );
        vetementDto.setMarque( vetement.getMarque() );
        vetementDto.setMediaId( vetement.getMediaId() );
        vetementDto.setUserId( vetement.getUserId() );
        vetementDto.setFavoris( vetement.getFavoris() );

        return vetementDto;
    }

    protected List<EnsembleResponseDto.VetementDto> vetementListToVetementDtoList(List<Vetement> list) {
        if ( list == null ) {
            return null;
        }

        List<EnsembleResponseDto.VetementDto> list1 = new ArrayList<EnsembleResponseDto.VetementDto>( list.size() );
        for ( Vetement vetement : list ) {
            list1.add( vetementToVetementDto( vetement ) );
        }

        return list1;
    }
}
