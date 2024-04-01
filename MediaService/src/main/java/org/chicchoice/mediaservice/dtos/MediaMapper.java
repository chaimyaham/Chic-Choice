package org.chicchoice.mediaservice.dtos;

import org.chicchoice.mediaservice.entities.Media;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MediaMapper {
    Media toEntity(MediaDto mediaDto);

    MediaDto toDto(Media media);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Media partialUpdate(MediaDto mediaDto, @MappingTarget Media media);
}