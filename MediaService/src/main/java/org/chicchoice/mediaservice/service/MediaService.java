package org.chicchoice.mediaservice.service;

import org.chicchoice.mediaservice.dtos.MediaDto;

public interface MediaService {
    MediaDto saveMedia(String imageUrl);
    MediaDto getMedialById(Long id);
    MediaDto updateMedia(Long id,String url);
    void deleteMediaById(Long id);


}
