package org.chicchoice.mediaservice.dtos;

import javax.annotation.processing.Generated;
import org.chicchoice.mediaservice.entities.Media;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-14T03:21:21+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class MediaMapperImpl implements MediaMapper {

    @Override
    public Media toEntity(MediaDto mediaDto) {
        if ( mediaDto == null ) {
            return null;
        }

        Media.MediaBuilder media = Media.builder();

        media.id( mediaDto.getId() );
        media.imageUrl( mediaDto.getImageUrl() );

        return media.build();
    }

    @Override
    public MediaDto toDto(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDto.MediaDtoBuilder mediaDto = MediaDto.builder();

        mediaDto.id( media.getId() );
        mediaDto.imageUrl( media.getImageUrl() );

        return mediaDto.build();
    }

    @Override
    public Media partialUpdate(MediaDto mediaDto, Media media) {
        if ( mediaDto == null ) {
            return media;
        }

        if ( mediaDto.getId() != null ) {
            media.setId( mediaDto.getId() );
        }
        if ( mediaDto.getImageUrl() != null ) {
            media.setImageUrl( mediaDto.getImageUrl() );
        }

        return media;
    }
}
