package org.chicchoice.vetementservice.mapper;

import org.chicchoice.vetementservice.dtos.VetementDto;
import org.chicchoice.vetementservice.entities.Vetement;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VetementMapper {

    VetementDto toDTO(Vetement vetement);
    Vetement toEntity(VetementDto vetementDto);
}
