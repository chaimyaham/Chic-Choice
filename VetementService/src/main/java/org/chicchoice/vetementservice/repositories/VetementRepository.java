package org.chicchoice.vetementservice.repositories;

import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetementRepository extends JpaRepository<Vetement, Long> {

    Optional<Vetement> findByMediaId(Long mediaId);
    Optional<Page<Vetement>> findAllByUserId(Long userId, Pageable pageable);
    Optional<Page<Vetement>> findAllByCategory(Category category, Pageable pageable);
    Optional<Page<Vetement>>findAllByUserIdAndCategory(Long userId,Category category,Pageable pageable);
    Optional<Page<Vetement>> findAllByUserIdAndFavoris(Long userId,boolean favoris,Pageable pageable);
    Optional<Page<Vetement>> findAllByCouleurIdAndUserId(String couleurId,Long userId,Pageable pageable);
}