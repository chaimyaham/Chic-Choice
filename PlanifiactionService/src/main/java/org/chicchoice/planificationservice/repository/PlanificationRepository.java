package org.chicchoice.planificationservice.repository;

import org.chicchoice.planificationservice.entities.Planification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {
}