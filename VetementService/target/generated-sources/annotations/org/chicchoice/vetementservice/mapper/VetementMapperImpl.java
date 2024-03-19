package org.chicchoice.vetementservice.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.chicchoice.vetementservice.dtos.request.VetementRequestDto;
import org.chicchoice.vetementservice.dtos.response.VetementResponseDto;
import org.chicchoice.vetementservice.entities.Ensemble;
import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.enums.Category;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T22:56:02+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class VetementMapperImpl implements VetementMapper {

    @Override
    public Vetement toEntity(VetementRequestDto vetementRequestDto) {
        if ( vetementRequestDto == null ) {
            return null;
        }

        Vetement.VetementBuilder vetement = Vetement.builder();

        vetement.note( vetementRequestDto.getNote() );
        vetement.category( vetementRequestDto.getCategory() );
        vetement.marque( vetementRequestDto.getMarque() );
        vetement.mediaId( vetementRequestDto.getMediaId() );
        vetement.userId( vetementRequestDto.getUserId() );
        vetement.favoris( vetementRequestDto.getFavoris() );

        return vetement.build();
    }

    @Override
    public VetementRequestDto toDto(Vetement vetement) {
        if ( vetement == null ) {
            return null;
        }

        VetementRequestDto vetementRequestDto = new VetementRequestDto();

        vetementRequestDto.setNote( vetement.getNote() );
        vetementRequestDto.setCategory( vetement.getCategory() );
        vetementRequestDto.setMarque( vetement.getMarque() );
        vetementRequestDto.setMediaId( vetement.getMediaId() );
        vetementRequestDto.setUserId( vetement.getUserId() );
        vetementRequestDto.setFavoris( vetement.getFavoris() );

        return vetementRequestDto;
    }

    @Override
    public Vetement partialUpdate(VetementRequestDto vetementRequestDto, Vetement vetement) {
        if ( vetementRequestDto == null ) {
            return vetement;
        }

        if ( vetementRequestDto.getNote() != null ) {
            vetement.setNote( vetementRequestDto.getNote() );
        }
        if ( vetementRequestDto.getCategory() != null ) {
            vetement.setCategory( vetementRequestDto.getCategory() );
        }
        if ( vetementRequestDto.getMarque() != null ) {
            vetement.setMarque( vetementRequestDto.getMarque() );
        }
        if ( vetementRequestDto.getMediaId() != null ) {
            vetement.setMediaId( vetementRequestDto.getMediaId() );
        }
        if ( vetementRequestDto.getUserId() != null ) {
            vetement.setUserId( vetementRequestDto.getUserId() );
        }
        if ( vetementRequestDto.getFavoris() != null ) {
            vetement.setFavoris( vetementRequestDto.getFavoris() );
        }

        return vetement;
    }

    @Override
    public Vetement toEntity(VetementResponseDto vetementResponseDto) {
        if ( vetementResponseDto == null ) {
            return null;
        }

        Vetement.VetementBuilder vetement = Vetement.builder();

        vetement.id( vetementResponseDto.getId() );
        vetement.note( vetementResponseDto.getNote() );
        vetement.date_d_ajout( vetementResponseDto.getDate_d_ajout() );
        vetement.category( vetementResponseDto.getCategory() );
        vetement.marque( vetementResponseDto.getMarque() );
        vetement.mediaId( vetementResponseDto.getMediaId() );
        vetement.userId( vetementResponseDto.getUserId() );
        vetement.favoris( vetementResponseDto.getFavoris() );
        vetement.ensembles( ensembleDtoListToEnsembleList( vetementResponseDto.getEnsembles() ) );

        return vetement.build();
    }

    @Override
    public VetementResponseDto toDto1(Vetement vetement) {
        if ( vetement == null ) {
            return null;
        }

        List<VetementResponseDto.EnsembleDto> ensembles = null;
        Long id = null;
        String note = null;
        LocalDateTime date_d_ajout = null;
        Category category = null;
        String marque = null;
        Long mediaId = null;
        Long userId = null;
        Boolean favoris = null;

        ensembles = ensembleListToEnsembleDtoList( vetement.getEnsembles() );
        id = vetement.getId();
        note = vetement.getNote();
        date_d_ajout = vetement.getDate_d_ajout();
        category = vetement.getCategory();
        marque = vetement.getMarque();
        mediaId = vetement.getMediaId();
        userId = vetement.getUserId();
        favoris = vetement.getFavoris();

        VetementResponseDto vetementResponseDto = new VetementResponseDto( id, note, date_d_ajout, category, marque, mediaId, userId, favoris, ensembles );

        return vetementResponseDto;
    }

    @Override
    public Vetement partialUpdate(VetementResponseDto vetementResponseDto, Vetement vetement) {
        if ( vetementResponseDto == null ) {
            return vetement;
        }

        if ( vetementResponseDto.getId() != null ) {
            vetement.setId( vetementResponseDto.getId() );
        }
        if ( vetementResponseDto.getNote() != null ) {
            vetement.setNote( vetementResponseDto.getNote() );
        }
        if ( vetementResponseDto.getDate_d_ajout() != null ) {
            vetement.setDate_d_ajout( vetementResponseDto.getDate_d_ajout() );
        }
        if ( vetementResponseDto.getCategory() != null ) {
            vetement.setCategory( vetementResponseDto.getCategory() );
        }
        if ( vetementResponseDto.getMarque() != null ) {
            vetement.setMarque( vetementResponseDto.getMarque() );
        }
        if ( vetementResponseDto.getMediaId() != null ) {
            vetement.setMediaId( vetementResponseDto.getMediaId() );
        }
        if ( vetementResponseDto.getUserId() != null ) {
            vetement.setUserId( vetementResponseDto.getUserId() );
        }
        if ( vetementResponseDto.getFavoris() != null ) {
            vetement.setFavoris( vetementResponseDto.getFavoris() );
        }
        if ( vetement.getEnsembles() != null ) {
            List<Ensemble> list = ensembleDtoListToEnsembleList( vetementResponseDto.getEnsembles() );
            if ( list != null ) {
                vetement.getEnsembles().clear();
                vetement.getEnsembles().addAll( list );
            }
        }
        else {
            List<Ensemble> list = ensembleDtoListToEnsembleList( vetementResponseDto.getEnsembles() );
            if ( list != null ) {
                vetement.setEnsembles( list );
            }
        }

        return vetement;
    }

    protected Ensemble ensembleDtoToEnsemble(VetementResponseDto.EnsembleDto ensembleDto) {
        if ( ensembleDto == null ) {
            return null;
        }

        Ensemble.EnsembleBuilder ensemble = Ensemble.builder();

        ensemble.id( ensembleDto.getId() );
        ensemble.createdAt( ensembleDto.getCreatedAt() );
        ensemble.nomDeLEnsemble( ensembleDto.getNomDeLEnsemble() );
        ensemble.utilisateurId( ensembleDto.getUtilisateurId() );
        ensemble.favoris( ensembleDto.getFavoris() );

        return ensemble.build();
    }

    protected List<Ensemble> ensembleDtoListToEnsembleList(List<VetementResponseDto.EnsembleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Ensemble> list1 = new ArrayList<Ensemble>( list.size() );
        for ( VetementResponseDto.EnsembleDto ensembleDto : list ) {
            list1.add( ensembleDtoToEnsemble( ensembleDto ) );
        }

        return list1;
    }

    protected VetementResponseDto.EnsembleDto ensembleToEnsembleDto(Ensemble ensemble) {
        if ( ensemble == null ) {
            return null;
        }

        Long id = null;
        LocalDateTime createdAt = null;
        String nomDeLEnsemble = null;
        Long utilisateurId = null;
        Boolean favoris = null;

        id = ensemble.getId();
        createdAt = ensemble.getCreatedAt();
        nomDeLEnsemble = ensemble.getNomDeLEnsemble();
        utilisateurId = ensemble.getUtilisateurId();
        favoris = ensemble.getFavoris();

        VetementResponseDto.EnsembleDto ensembleDto = new VetementResponseDto.EnsembleDto( id, createdAt, nomDeLEnsemble, utilisateurId, favoris );

        return ensembleDto;
    }

    protected List<VetementResponseDto.EnsembleDto> ensembleListToEnsembleDtoList(List<Ensemble> list) {
        if ( list == null ) {
            return null;
        }

        List<VetementResponseDto.EnsembleDto> list1 = new ArrayList<VetementResponseDto.EnsembleDto>( list.size() );
        for ( Ensemble ensemble : list ) {
            list1.add( ensembleToEnsembleDto( ensemble ) );
        }

        return list1;
    }
}
