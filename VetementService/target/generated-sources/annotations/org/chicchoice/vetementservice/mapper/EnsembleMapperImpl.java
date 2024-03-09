package org.chicchoice.vetementservice.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.chicchoice.vetementservice.dtos.EnsembleDto;
import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.entities.Ensemble;
import org.chicchoice.vetementservice.entities.Vetement;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-09T23:47:54+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class EnsembleMapperImpl implements EnsembleMapper {

    @Override
    public EnsembleDto toDTO(Ensemble ensemble) {
        if ( ensemble == null ) {
            return null;
        }

        EnsembleDto.EnsembleDtoBuilder ensembleDto = EnsembleDto.builder();

        ensembleDto.id( ensemble.getId() );
        ensembleDto.createdAt( ensemble.getCreatedAt() );
        ensembleDto.nomDeLEnsemble( ensemble.getNomDeLEnsemble() );
        ensembleDto.utilisateurId( ensemble.getUtilisateurId() );
        ensembleDto.favoris( ensemble.getFavoris() );
        ensembleDto.vetements( vetementListToVetementDtoList( ensemble.getVetements() ) );

        return ensembleDto.build();
    }

    @Override
    public Ensemble toEntity(EnsembleDto ensembleDto) {
        if ( ensembleDto == null ) {
            return null;
        }

        Ensemble.EnsembleBuilder ensemble = Ensemble.builder();

        ensemble.id( ensembleDto.getId() );
        ensemble.createdAt( ensembleDto.getCreatedAt() );
        ensemble.nomDeLEnsemble( ensembleDto.getNomDeLEnsemble() );
        ensemble.utilisateurId( ensembleDto.getUtilisateurId() );
        ensemble.favoris( ensembleDto.getFavoris() );
        ensemble.vetements( vetementDtoListToVetementList( ensembleDto.getVetements() ) );

        return ensemble.build();
    }

    protected List<EnsembleDto> ensembleListToEnsembleDtoList(List<Ensemble> list) {
        if ( list == null ) {
            return null;
        }

        List<EnsembleDto> list1 = new ArrayList<EnsembleDto>( list.size() );
        for ( Ensemble ensemble : list ) {
            list1.add( toDTO( ensemble ) );
        }

        return list1;
    }

    protected VetementDto vetementToVetementDto(Vetement vetement) {
        if ( vetement == null ) {
            return null;
        }

        VetementDto.VetementDtoBuilder vetementDto = VetementDto.builder();

        vetementDto.id( vetement.getId() );
        vetementDto.note( vetement.getNote() );
        vetementDto.date_d_ajout( vetement.getDate_d_ajout() );
        vetementDto.category( vetement.getCategory() );
        vetementDto.marque( vetement.getMarque() );
        vetementDto.mediaId( vetement.getMediaId() );
        vetementDto.userId( vetement.getUserId() );
        vetementDto.favoris( vetement.getFavoris() );
        vetementDto.ensembles( ensembleListToEnsembleDtoList( vetement.getEnsembles() ) );

        return vetementDto.build();
    }

    protected List<VetementDto> vetementListToVetementDtoList(List<Vetement> list) {
        if ( list == null ) {
            return null;
        }

        List<VetementDto> list1 = new ArrayList<VetementDto>( list.size() );
        for ( Vetement vetement : list ) {
            list1.add( vetementToVetementDto( vetement ) );
        }

        return list1;
    }

    protected List<Ensemble> ensembleDtoListToEnsembleList(List<EnsembleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Ensemble> list1 = new ArrayList<Ensemble>( list.size() );
        for ( EnsembleDto ensembleDto : list ) {
            list1.add( toEntity( ensembleDto ) );
        }

        return list1;
    }

    protected Vetement vetementDtoToVetement(VetementDto vetementDto) {
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
        vetement.ensembles( ensembleDtoListToEnsembleList( vetementDto.getEnsembles() ) );

        return vetement.build();
    }

    protected List<Vetement> vetementDtoListToVetementList(List<VetementDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Vetement> list1 = new ArrayList<Vetement>( list.size() );
        for ( VetementDto vetementDto : list ) {
            list1.add( vetementDtoToVetement( vetementDto ) );
        }

        return list1;
    }
}
