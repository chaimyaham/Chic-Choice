package org.chicchoice.planificationservice.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.chicchoice.planificationservice.dtos.PlanificationDto;
import org.chicchoice.planificationservice.entities.Planification;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T23:35:21+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class PlanificationMapperImpl implements PlanificationMapper {

    @Override
    public Planification toEntity(PlanificationDto planificationDto) {
        if ( planificationDto == null ) {
            return null;
        }

        Planification planification = new Planification();

        planification.setId( planificationDto.getId() );
        planification.setDateDebut( planificationDto.getDateDebut() );
        planification.setDateFin( planificationDto.getDateFin() );
        planification.setDescription( planificationDto.getDescription() );
        planification.setUtilisateurId( planificationDto.getUtilisateurId() );
        planification.setMeteoId( planificationDto.getMeteoId() );
        List<Long> list = planificationDto.getEnsemblesIds();
        if ( list != null ) {
            planification.setEnsemblesIds( new ArrayList<Long>( list ) );
        }

        return planification;
    }

    @Override
    public PlanificationDto toDto(Planification planification) {
        if ( planification == null ) {
            return null;
        }

        PlanificationDto.PlanificationDtoBuilder planificationDto = PlanificationDto.builder();

        planificationDto.id( planification.getId() );
        planificationDto.dateDebut( planification.getDateDebut() );
        planificationDto.dateFin( planification.getDateFin() );
        planificationDto.description( planification.getDescription() );
        planificationDto.utilisateurId( planification.getUtilisateurId() );
        planificationDto.meteoId( planification.getMeteoId() );
        List<Long> list = planification.getEnsemblesIds();
        if ( list != null ) {
            planificationDto.ensemblesIds( new ArrayList<Long>( list ) );
        }

        return planificationDto.build();
    }

    @Override
    public Planification partialUpdate(PlanificationDto planificationDto, Planification planification) {
        if ( planificationDto == null ) {
            return planification;
        }

        if ( planificationDto.getId() != null ) {
            planification.setId( planificationDto.getId() );
        }
        if ( planificationDto.getDateDebut() != null ) {
            planification.setDateDebut( planificationDto.getDateDebut() );
        }
        if ( planificationDto.getDateFin() != null ) {
            planification.setDateFin( planificationDto.getDateFin() );
        }
        if ( planificationDto.getDescription() != null ) {
            planification.setDescription( planificationDto.getDescription() );
        }
        if ( planificationDto.getUtilisateurId() != null ) {
            planification.setUtilisateurId( planificationDto.getUtilisateurId() );
        }
        if ( planificationDto.getMeteoId() != null ) {
            planification.setMeteoId( planificationDto.getMeteoId() );
        }
        if ( planification.getEnsemblesIds() != null ) {
            List<Long> list = planificationDto.getEnsemblesIds();
            if ( list != null ) {
                planification.getEnsemblesIds().clear();
                planification.getEnsemblesIds().addAll( list );
            }
        }
        else {
            List<Long> list = planificationDto.getEnsemblesIds();
            if ( list != null ) {
                planification.setEnsemblesIds( new ArrayList<Long>( list ) );
            }
        }

        return planification;
    }
}
