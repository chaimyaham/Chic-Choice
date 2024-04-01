package org.chicchoice.mediaservice.repository;

import org.chicchoice.mediaservice.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByImageUrl(String url);
}
