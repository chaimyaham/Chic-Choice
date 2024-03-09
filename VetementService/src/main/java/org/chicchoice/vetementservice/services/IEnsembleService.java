package org.chicchoice.vetementservice.services;

import org.chicchoice.vetementservice.dtos.EnsembleDto;

import java.util.List;

public interface IEnsembleService {
    EnsembleDto ajouterUnVetementAUnEnsemble(Long vetementId, Long ensembleId);
    EnsembleDto supprimerUnVetementDunEnsemble(Long vetementId, Long ensembleId);
    void supprimerUnEnsemble(Long ensembleId);
    void marquerUnEnsembleCommeFavori(Long ensembleId);
    EnsembleDto creerEtAjouterUnEnsemble(EnsembleDto ensembleDto);
    List<EnsembleDto> getAllEnsembles();
    List<EnsembleDto> getEnsemblesFavorisByUserID(Long userId);

}
