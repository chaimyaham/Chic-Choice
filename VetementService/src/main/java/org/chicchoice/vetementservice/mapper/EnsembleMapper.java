package org.chicchoice.vetementservice.mapper;

import org.chicchoice.vetementservice.dtos.request.EnsembleRequestDto;
import org.chicchoice.vetementservice.dtos.response.EnsembleResponseDto;
import org.chicchoice.vetementservice.entities.Ensemble;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnsembleMapper {
    Ensemble toEntity(EnsembleRequestDto ensembleRequestDto);

    EnsembleRequestDto toDto(Ensemble ensemble);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ensemble partialUpdate(EnsembleRequestDto ensembleRequestDto, @MappingTarget Ensemble ensemble);

    Ensemble toEntity(EnsembleResponseDto ensembleResponseDto);

    EnsembleResponseDto toDto1(Ensemble ensemble);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ensemble partialUpdate(EnsembleResponseDto ensembleResponseDto, @MappingTarget Ensemble ensemble);
}