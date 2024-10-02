package com.dme.DormitoryProject.dtos.logLevelDtos;

import com.dme.DormitoryProject.entity.LogLevel;

public class LogLevelMapper {
    public static LogLevel toEntity(LogLevelDTO logLevelDTO){
        LogLevel logLevel = new LogLevel();

        logLevel.setDescription(logLevelDTO.getDescription());

        return  logLevel;
    }
    public static LogLevelDTO toDTO(LogLevel logLevel){
        LogLevelDTO logLevelDTO = new LogLevelDTO();

        logLevelDTO.setId(logLevel.getId());
        logLevelDTO.setDescription(logLevel.getDescription());

        return logLevelDTO;
    }
}
