package org.chicchoice.vetementservice.mapper;

import org.chicchoice.vetementservice.dtos.request.VetementRequestDto;
import org.chicchoice.vetementservice.dtos.response.VetementResponseDto;
import org.chicchoice.vetementservice.entities.Vetement;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VetementMapper {
    Vetement toEntity(VetementRequestDto vetementRequestDto);

    VetementRequestDto toDto(Vetement vetement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Vetement partialUpdate(VetementRequestDto vetementRequestDto, @MappingTarget Vetement vetement);

    Vetement toEntity(VetementResponseDto vetementResponseDto);

    VetementResponseDto toDto1(Vetement vetement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Vetement partialUpdate(VetementResponseDto vetementResponseDto, @MappingTarget Vetement vetement);
}