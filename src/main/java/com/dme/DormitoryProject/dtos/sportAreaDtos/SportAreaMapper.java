package com.dme.DormitoryProject.dtos.sportAreaDtos;

import com.dme.DormitoryProject.entity.SportArea;

public class SportAreaMapper {

    public static SportArea toEntity(SportAreaDTO sportAreaDTO){
        SportArea sportArea = new SportArea();

        sportArea.setSporType(sportAreaDTO.getSportType());

        return sportArea;
    }
    public static  SportAreaDTO toDTO(SportArea sportArea){
        SportAreaDTO sportAreaDTO = new SportAreaDTO();

        sportAreaDTO.setId(sportArea.getId());
        sportAreaDTO.setSportType(sportArea.getSporType());

        return sportAreaDTO;
    }
}
