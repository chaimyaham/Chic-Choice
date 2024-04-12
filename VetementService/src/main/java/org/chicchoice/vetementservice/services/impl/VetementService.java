package org.chicchoice.vetementservice.services.impl;


import com.simplon.couleur.CouleurClient;
import com.simplon.couleur.CouleurDto;
import com.simplon.media.MediaClient;
import com.simplon.media.MediaDto;
import lombok.AllArgsConstructor;
import org.chicchoice.vetementservice.dtos.response.VetementResponseDto;
import org.chicchoice.vetementservice.entities.Vetement;
import org.chicchoice.vetementservice.enums.Category;
import org.chicchoice.vetementservice.exeptions.ResourceNotFoundException;
import org.chicchoice.vetementservice.exeptions.ServiceException;
import org.chicchoice.vetementservice.mapper.VetementMapper;
import org.chicchoice.vetementservice.repositories.VetementRepository;
import org.chicchoice.vetementservice.dtos.request.VetementRequestDto;
import org.chicchoice.vetementservice.services.IVetementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class VetementService implements IVetementService {
    private final VetementRepository vetementRepository;
    private final VetementMapper vetementMapper;
    private final EnsembleService ensembleService;
    private final MediaClient mediaClient;
    private final CouleurClient couleurClient;
    private static final Logger logger = LoggerFactory.getLogger(VetementService.class);


    @Override
    public Page<VetementResponseDto> getAllVetements(Pageable pageable) {
        try{
            Page<Vetement> vetements = vetementRepository.findAll(pageable);
            logger.info("List des vetements recupere avec succes");
            return vetements.map(vetementMapper::toDto1);
        }catch(Exception e){
            logger.error("Error encontre lors de la recuperation de la liste des vetements ");
            throw new ServiceException("Vetements","Une erreur s'est produite lors de la recuperation de tous les vetements.", e);
        }
    }


    @Override
    public Page<VetementResponseDto> getAllByUserId(Long userId, Pageable pageable) {
        try{
            Optional<Page<Vetement>> vetementsCreerParUser = vetementRepository.findAllByUserId(userId,pageable);
            logger.info("recuperation de la liste des vetement avec success");
            return vetementsCreerParUser.map(vetements->vetements.map(vetementMapper::toDto1)).orElseGet(Page::empty);
        }catch (Exception e){
            logger.error("Error encontre lors de la recuperation de la liste des vetement ");
            throw new ServiceException("Vetement se","Une erreur s'est produite lors de la recuperation de tous les vetements.", e);
        }
    }

    @Override
    public VetementResponseDto createVetement(VetementRequestDto vetementRequestDto) {
//            check if the couleurid exist on couleur db
            ResponseEntity<CouleurDto> couleurExist=couleurClient.getColorById(vetementRequestDto.getCouleurId());
            if (!couleurExist.getStatusCode().is2xxSuccessful()) {
                logger.error("color with  that id :{} doesn't exist ",vetementRequestDto.getCouleurId());
                throw new ResourceNotFoundException("Couleur","La couleur avec cet identifiant  n'a pas ete trouvee dans service couleur",vetementRequestDto.getCouleurId());
            }
            //check if mediaId exist on media db
            ResponseEntity<MediaDto> mediaExist=mediaClient.getMediaById(vetementRequestDto.getMediaId());
            if(!mediaExist.getStatusCode().is2xxSuccessful()){
                logger.error("media with  that id :{} doesn't exist ",vetementRequestDto.getMediaId());
                throw new ResourceNotFoundException("Media","La media avec cet identifiant  n'a pas ete trouvee dans service media",vetementRequestDto.getCouleurId());
            }

            Vetement article=vetementMapper.toEntity(vetementRequestDto);
            article.setDate_d_ajout(LocalDateTime.now());
            Vetement savedVetement = vetementRepository.save(article);
            logger.info("Vetement ajouter avec success");
            return vetementMapper.toDto1(savedVetement);

    }

    @Override
    public VetementResponseDto getVetementById(Long id) {
        try {

            Optional<Vetement> article = vetementRepository.findById(id);
            if (article.isEmpty()){
                logger.error("article avec cet id n'exist pas : {}",id);
                throw new ResourceNotFoundException("vetement service","article with that id nexist pas",id.toString());
            }
            logger.info("recupere l'article avec cet id {} avec succes",id);
            return vetementMapper.toDto1(article.get());

        }catch (Exception e){
            logger.error("Error encontre lors de la recuperation de cet article");
            throw new ServiceException("Vetement service","Une erreur s'est produite lors de la recuperation de cet article", e);
        }

    }

    @Override
    public void deleteVetementById(Long id) {
        try{
            Optional<Vetement> article = vetementRepository.findById(id);
            if (article.isEmpty()){
                logger.error("article with that id n'exist pas : {}",id);
                throw new ResourceNotFoundException("vetements","article with that id nexist pas",id.toString());
            }
            Vetement vetement = article.get();
            ensembleService.supprimerVetementDeTousEnsembles(vetement);
            vetementRepository.deleteById(id);
            logger.info("article supprimer avec succes");

        }catch (Exception e){
            logger.error("Error encontre lors de la suppression de cet article");
            throw new ServiceException("Vetement S","Une erreur s'est produite lors de la suppression de cet article", e);
        }

    }

    @Override
    public void marquerVetementCommeFavori(Long vetementId) {
        try {
            // Verifier si l'article existe
            vetementRepository.findById(vetementId).ifPresentOrElse(vetement -> {
                vetement.setFavoris(!vetement.isFavoris());
                    vetementRepository.save(vetement);
                    logger.info("vetement marque comme favoris  : {}", vetementId);

            }, () -> {
                logger.error("vetement non trouvé avec cet ID : {}", vetementId);
                throw new ResourceNotFoundException("vetement", "avec cet ID n'existe pas", vetementId.toString());
            });
        } catch (Exception e) {
            logger.error("Erreur lors du marquage du vetement comme favori");
            throw new ServiceException("vetement", "Une erreur est produite lors du marquage de l article comme favori.", e);
        }

    }

    @Override
    public Page<VetementResponseDto> getVetementsByCategory(Category category,Pageable pageable) {
        try{
            Optional<Page<Vetement>> articlesParCategory=vetementRepository.findAllByCategory(category,pageable);
            logger.info("fetching la liste des vetements par category avec success");
            return articlesParCategory.map(articles->articles.map(vetementMapper::toDto1)).orElseGet(Page::empty);
        }catch (Exception e){
            logger.error("Error encontre lors de la recuperation de la liste des vetement form db");
            throw new ServiceException("Vetement SERVICE","Une erreur s'est produite lors de la recuperation de tous les vetements parCategory.", e);
        }

    }

    @Override
    public Page<VetementResponseDto> getVetementsFavorisByUserId(Long userId,Pageable pageable) {
        try{

            Optional<Page<Vetement>> articleFavorisByUser = vetementRepository.findAllByUserIdAndFavoris(userId,true,pageable);
            logger.info("fetching all articles favoris by {}",userId);
            return articleFavorisByUser.map(articles->articles.map(vetementMapper::toDto1)).orElseGet(Page::empty);
        }catch(Exception e){
            logger.error("Error encontre lors de la recuperation de la liste des vetements");
            throw new ServiceException("Vetement service","Une erreur s'est produite lors de la recuperation de tous les vetements par favoris.", e);
        }

    }

    @Override
    public Page<VetementResponseDto> getAllByCategoryAndUserId(Long userId, Category category, Pageable pageable) {
        try{
            //todo check if user with that id exist first
            Optional<Page<Vetement>> articlesByCategory = vetementRepository.findAllByUserIdAndCategory(userId,category,pageable);
            logger.info("fetching all articles by category {} of that{}",category,userId);
            return articlesByCategory.map(articles->articles.map(vetementMapper::toDto1)).orElseGet(Page::empty);
        }catch(Exception e){
            logger.error("Error encontre lors de la recuperation de la liste des vetement");
            throw new ServiceException("Vetement","Une erreur s'est produite lors de la recuperation de tous les vetements parCategory.", e);
        }
    }

    @Override
    public Page<VetementResponseDto> getAllByColorIDandUserID(String couleurId, Long userId,Pageable pageable) {
        try{
            //todo check if user with that id exist first
            Optional<Page<Vetement>> articlesByColor = vetementRepository.findAllByCouleurIdAndUserId(couleurId,userId,pageable);
            logger.info("fetching all articles by color of id  {} of that user with id :{}",couleurId,userId);
            return articlesByColor.map(articles->articles.map(vetementMapper::toDto1)).orElseGet(Page::empty);
        }catch(Exception e){
            logger.error("Error encontre lors de la recuperation de la liste des vetement par couleur");
            throw new ServiceException("Vetement","Une erreur s'est produite lors de la recuperation de tous les vetements par Couleur.", e);
        }
    }

    @Override
    public VetementResponseDto modifierVetement(Long id, VetementRequestDto vetementRequestDto) {
        try{

            Optional<Vetement> vetement =vetementRepository.findById(id);
            if(vetement.isEmpty()){
                logger.error("Vetement with that id {} does not exist",id);
                throw new ResourceNotFoundException("vetement","Vetement with that id does not exist",id.toString());
            }
            Vetement existingVetement = vetement.get();
            existingVetement.setNote(vetementRequestDto.getNote());
            existingVetement.setMediaId(vetementRequestDto.getMediaId());
            existingVetement.setFavoris(vetementRequestDto.getFavoris());
            existingVetement.setMarque(vetementRequestDto.getMarque());
            existingVetement.setCategory(vetementRequestDto.getCategory());
            existingVetement.setDate_d_ajout(LocalDateTime.now());

            Vetement savedVetement = vetementRepository.save(existingVetement);
            logger.info("Vetement mis a jour avec success");
            return vetementMapper.toDto1(savedVetement);
        }catch(Exception e){
            logger.error("Error encontre lors de la modification de cet article");
            throw new ServiceException("Vetement","Une erreur s'est produite lors de la modification de cet article.", e);
        }
    }
}
