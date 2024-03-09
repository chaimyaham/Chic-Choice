package org.chicchoice.vetementservice.services.impl;

import org.chicchoice.vetementservice.dtos.EnsembleDto;
import org.chicchoice.vetementservice.services.IEnsembleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnsembleService implements IEnsembleService {
    @Override
    public EnsembleDto ajouterUnVetementAUnEnsemble(Long vetementId, Long ensembleId) {
        return null;
    }

    @Override
    public EnsembleDto supprimerUnVetementDunEnsemble(Long vetementId, Long ensembleId) {
        return null;
    }

    @Override
    public void supprimerUnEnsemble(Long ensembleId) {

    }

    @Override
    public void marquerUnEnsembleCommeFavori(Long ensembleId) {

    }

    @Override
    public EnsembleDto creerEtAjouterUnEnsemble(EnsembleDto ensembleDto) {
        return null;
    }

    @Override
    public List<EnsembleDto> getAllEnsembles() {
        return null;
    }

    @Override
    public List<EnsembleDto> getEnsemblesFavorisByUserID(Long userId) {
        return null;
    }
}
