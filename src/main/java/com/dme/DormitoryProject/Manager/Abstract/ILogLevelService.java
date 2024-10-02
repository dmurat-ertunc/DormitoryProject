package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.logLevelDtos.LogLevelDTO;
import com.dme.DormitoryProject.entity.LogLevel;

import java.util.List;
import java.util.Optional;

public interface ILogLevelService {
    List<LogLevelDTO> getAll();
    Optional<LogLevelDTO> getById(Long id);
    LogLevel saveLogLevel(LogLevelDTO logLevelDTO);
    LogLevel updateLogLevel(Long id,LogLevelDTO logLevelDTO);
    LogLevel deleteLogLevel(Long id);

}
