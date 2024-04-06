package org.chicchoice.CouleurService.services;

import org.chicchoice.CouleurService.dto.CouleurDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouleurService {

    Page<CouleurDto> getAll(Pageable pageable);
    CouleurDto getColorById(String id);
}
