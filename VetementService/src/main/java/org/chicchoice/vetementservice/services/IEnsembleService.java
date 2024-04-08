package org.chicchoice.vetementservice.services;

import org.chicchoice.vetementservice.dtos.request.EnsembleRequestDto;
import org.chicchoice.vetementservice.dtos.response.EnsembleResponseDto;
import org.chicchoice.vetementservice.entities.Vetement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEnsembleService {
    EnsembleResponseDto ajouterUnVetementAUnEnsemble(Long vetementId, Long ensembleId);
    EnsembleResponseDto supprimerUnVetementDunEnsemble(Long vetementId, Long ensembleId);
    void supprimerUnEnsemble(Long ensembleId);
    void marquerUnEnsembleCommeFavori(Long ensembleId);
    EnsembleResponseDto creerEtAjouterUnEnsemble(EnsembleRequestDto ensembleRequestDto);
    Page<EnsembleResponseDto> getAllEnsembles(Pageable pageable);
    Page<EnsembleResponseDto> getEnsemblesByUserID(Long userId,Pageable pageable);
    Page<EnsembleResponseDto>getEnsemblesFavorisByUserId(Long userId,Pageable pageable);
    void supprimerVetementDeTousEnsembles(Vetement vetement);
    //todo ajouter la method pour recuperer la list des vetement deja porter dans une temperature percise
   EnsembleResponseDto getEnsembleById(Long id);
    EnsembleResponseDto modifierEnsemble(Long id,EnsembleRequestDto ensembleRequestDto);

}
