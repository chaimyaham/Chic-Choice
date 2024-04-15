package org.chicchoice.vetementservice.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.dtos.request.VetementRequestDto;
import org.chicchoice.vetementservice.dtos.response.VetementResponseDto;
import org.chicchoice.vetementservice.entities.Ensemble;
import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.enums.Category;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-14T01:52:47+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class VetementMapperImpl implements VetementMapper {

    @Override
    public Vetement toEntity(VetementDto vetementDto) {
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
        vetement.couleurId( vetementDto.getCouleurId() );
        vetement.ensembles( ensembleDto1ListToEnsembleList( vetementDto.getEnsembles() ) );

        return vetement.build();
    }

    @Override
    public VetementDto toDto(Vetement vetement) {
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
        vetementDto.couleurId( vetement.getCouleurId() );
        vetementDto.ensembles( ensembleListToEnsembleDto1List( vetement.getEnsembles() ) );

        return vetementDto.build();
    }

    @Override
    public Vetement partialUpdate(VetementDto vetementDto, Vetement vetement) {
        if ( vetementDto == null ) {
            return vetement;
        }

        if ( vetementDto.getId() != null ) {
            vetement.setId( vetementDto.getId() );
        }
        if ( vetementDto.getNote() != null ) {
            vetement.setNote( vetementDto.getNote() );
        }
        if ( vetementDto.getDate_d_ajout() != null ) {
            vetement.setDate_d_ajout( vetementDto.getDate_d_ajout() );
        }
        if ( vetementDto.getCategory() != null ) {
            vetement.setCategory( vetementDto.getCategory() );
        }
        if ( vetementDto.getMarque() != null ) {
            vetement.setMarque( vetementDto.getMarque() );
        }
        if ( vetementDto.getMediaId() != null ) {
            vetement.setMediaId( vetementDto.getMediaId() );
        }
        if ( vetementDto.getUserId() != null ) {
            vetement.setUserId( vetementDto.getUserId() );
        }
        if ( vetementDto.getFavoris() != null ) {
            vetement.setFavoris( vetementDto.getFavoris() );
        }
        if ( vetementDto.getCouleurId() != null ) {
            vetement.setCouleurId( vetementDto.getCouleurId() );
        }
        if ( vetement.getEnsembles() != null ) {
            List<Ensemble> list = ensembleDto1ListToEnsembleList( vetementDto.getEnsembles() );
            if ( list != null ) {
                vetement.getEnsembles().clear();
                vetement.getEnsembles().addAll( list );
            }
        }
        else {
            List<Ensemble> list = ensembleDto1ListToEnsembleList( vetementDto.getEnsembles() );
            if ( list != null ) {
                vetement.setEnsembles( list );
            }
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
        vetement.couleurId( vetementResponseDto.getCouleurId() );
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
        String couleurId = null;

        ensembles = ensembleListToEnsembleDtoList( vetement.getEnsembles() );
        id = vetement.getId();
        note = vetement.getNote();
        date_d_ajout = vetement.getDate_d_ajout();
        category = vetement.getCategory();
        marque = vetement.getMarque();
        mediaId = vetement.getMediaId();
        userId = vetement.getUserId();
        favoris = vetement.getFavoris();
        couleurId = vetement.getCouleurId();

        VetementResponseDto vetementResponseDto = new VetementResponseDto( id, note, date_d_ajout, category, marque, mediaId, userId, favoris, couleurId, ensembles );

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
        if ( vetementResponseDto.getCouleurId() != null ) {
            vetement.setCouleurId( vetementResponseDto.getCouleurId() );
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
        vetement.couleurId( vetementRequestDto.getCouleurId() );

        return vetement.build();
    }

    @Override
    public VetementRequestDto toDto2(Vetement vetement) {
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
        vetementRequestDto.setCouleurId( vetement.getCouleurId() );

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
        if ( vetementRequestDto.getCouleurId() != null ) {
            vetement.setCouleurId( vetementRequestDto.getCouleurId() );
        }

        return vetement;
    }

    protected Ensemble ensembleDto1ToEnsemble(VetementDto.EnsembleDto1 ensembleDto1) {
        if ( ensembleDto1 == null ) {
            return null;
        }

        Ensemble.EnsembleBuilder ensemble = Ensemble.builder();

        ensemble.id( ensembleDto1.getId() );
        ensemble.createdAt( ensembleDto1.getCreatedAt() );
        ensemble.nomDeLEnsemble( ensembleDto1.getNomDeLEnsemble() );
        ensemble.utilisateurId( ensembleDto1.getUtilisateurId() );
        ensemble.favoris( ensembleDto1.getFavoris() );

        return ensemble.build();
    }

    protected List<Ensemble> ensembleDto1ListToEnsembleList(List<VetementDto.EnsembleDto1> list) {
        if ( list == null ) {
            return null;
        }

        List<Ensemble> list1 = new ArrayList<Ensemble>( list.size() );
        for ( VetementDto.EnsembleDto1 ensembleDto1 : list ) {
            list1.add( ensembleDto1ToEnsemble( ensembleDto1 ) );
        }

        return list1;
    }

    protected VetementDto.EnsembleDto1 ensembleToEnsembleDto1(Ensemble ensemble) {
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

        VetementDto.EnsembleDto1 ensembleDto1 = new VetementDto.EnsembleDto1( id, createdAt, nomDeLEnsemble, utilisateurId, favoris );

        return ensembleDto1;
    }

    protected List<VetementDto.EnsembleDto1> ensembleListToEnsembleDto1List(List<Ensemble> list) {
        if ( list == null ) {
            return null;
        }

        List<VetementDto.EnsembleDto1> list1 = new ArrayList<VetementDto.EnsembleDto1>( list.size() );
        for ( Ensemble ensemble : list ) {
            list1.add( ensembleToEnsembleDto1( ensemble ) );
        }

        return list1;
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
