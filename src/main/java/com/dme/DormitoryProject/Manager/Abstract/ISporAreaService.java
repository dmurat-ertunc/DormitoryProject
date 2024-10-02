package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaDTO;
import com.dme.DormitoryProject.entity.SportArea;

import java.util.List;
import java.util.Optional;

public interface ISporAreaService {
    List<SportAreaDTO> getAll();
    Optional<SportAreaDTO> getById(Long id);
    SportArea saveSportArea(SportAreaDTO sportAreaDTO);
    SportArea updateSportArea(Long id,SportAreaDTO sportAreaDTO);
    SportArea deleteSportArea(Long id);

}
