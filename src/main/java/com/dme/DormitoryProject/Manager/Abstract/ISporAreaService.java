package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaDTO;
import com.dme.DormitoryProject.entity.SportArea;
import com.dme.DormitoryProject.response.Result;

import java.util.List;
import java.util.Optional;

public interface ISporAreaService {
    Result getAll();
    Result getById(Long id);
    Result saveSportArea(SportAreaDTO sportAreaDTO);
    Result updateSportArea(Long id,SportAreaDTO sportAreaDTO);
    Result deleteSportArea(Long id);

}
