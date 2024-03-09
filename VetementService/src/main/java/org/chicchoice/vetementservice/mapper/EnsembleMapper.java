package org.chicchoice.vetementservice.mapper;

import org.chicchoice.vetementservice.dtos.EnsembleDto;
import org.chicchoice.vetementservice.entities.Ensemble;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EnsembleMapper {

    EnsembleDto toDTO(Ensemble ensemble);
    Ensemble toEntity(EnsembleDto ensembleDto);
}
