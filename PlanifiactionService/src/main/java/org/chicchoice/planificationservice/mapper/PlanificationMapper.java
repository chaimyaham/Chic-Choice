package org.chicchoice.planificationservice.mapper;

import org.chicchoice.planificationservice.dtos.PlanificationDto;
import org.chicchoice.planificationservice.entities.Planification;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlanificationMapper {
    Planification toEntity(PlanificationDto planificationDto);

    PlanificationDto toDto(Planification planification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Planification partialUpdate(PlanificationDto planificationDto, @MappingTarget Planification planification);
}