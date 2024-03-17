package org.chicchoice.vetementservice.services;

import org.chicchoice.vetementservice.dtos.EnsembleDto;
import org.chicchoice.vetementservice.dtos.request.EnsembleRequestDto;
import org.chicchoice.vetementservice.dtos.response.EnsembleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEnsembleService {
    EnsembleResponseDto ajouterUnVetementAUnEnsemble(Long vetementId, Long ensembleId);
    EnsembleResponseDto supprimerUnVetementDunEnsemble(Long vetementId, Long ensembleId);
    void supprimerUnEnsemble(Long ensembleId);
    void marquerUnEnsembleCommeFavori(Long ensembleId);
    EnsembleResponseDto creerEtAjouterUnEnsemble(EnsembleRequestDto ensembleRequestDto);
    Page<EnsembleResponseDto> getAllEnsembles(Pageable pageable);
    Page<EnsembleResponseDto> getEnsemblesByUserID(Long userId,Pageable pageable);
    Page<EnsembleResponseDto>getEnsemblesFavorisByUserId(Long userId,Pageable pageable);
    //todo ajouter la method pour recuperer la list des vetement deja porter dans une temperature percise


}
