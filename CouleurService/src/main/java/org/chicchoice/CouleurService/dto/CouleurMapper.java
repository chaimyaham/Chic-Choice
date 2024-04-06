package org.chicchoice.CouleurService.dto;

import org.chicchoice.CouleurService.entities.Couleur;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CouleurMapper {
    Couleur toEntity(CouleurDto couleurDto);

    CouleurDto toDto(Couleur couleur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Couleur partialUpdate(CouleurDto couleurDto, @MappingTarget Couleur couleur);
}