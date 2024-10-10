package com.dme.DormitoryProject.dtos.lgoDtos;

import com.dme.DormitoryProject.entity.Lgo;
import org.apache.juli.logging.Log;

public class LgoMapper {
    public static LgoDTO toDTO(Lgo lgo){
        LgoDTO lgoDTO = new LgoDTO();

        lgoDTO.setId(lgo.getId());
        lgoDTO.setMessage(lgo.getMessage());
        lgoDTO.setLogLeveId(lgo.getLogLevel().getId());
        lgoDTO.setLogLevelDescription(lgo.getLogLevel().getDescription());
        lgoDTO.setDate(lgo.getAddDate());

        return lgoDTO;
    }
    public static Lgo toEntity(LgoDTO lgoDTO){
        Lgo lgo = new Lgo();

        lgo.setMessage(lgoDTO.getMessage());
        lgo.getLogLevel().setDescription(lgoDTO.getLogLevelDescription());;
        lgo.getLogLevel().setId(lgoDTO.getLogLeveId());

        return lgo;
    }
}
