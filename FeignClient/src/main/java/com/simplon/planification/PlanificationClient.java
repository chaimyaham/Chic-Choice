package com.simplon.planification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(name = "PLANIFICATION")
public interface PlanificationClient {

    @GetMapping("/api/v1/planfications/all/ensemble/{ensembleId}")
    ResponseEntity<List<PlanificationDto>> getPlanificationsWithEnsembleId(
            @PathVariable("ensembleId") Long ensembleId
    );
    @DeleteMapping("/api/v1/planfications/{idPlanification}/ensembles/{idEnsemble}")
     ResponseEntity<Void> supprimerEnsembleDePlanification(@PathVariable("idPlanification") Long idPlanification, @PathVariable("idEnsemble") Long idEnsemble);

}
