package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.entity.SportArea;

import java.util.List;
import java.util.Optional;

public interface ISporAreaService {
    List<SportArea> getAll();
    Optional<SportArea> getById(Long id);
    SportArea saveSportArea(SportArea sportArea);
    SportArea updateSportArea(Long id,SportArea sportArea);
    SportArea deleteSportArea(Long id);

}
