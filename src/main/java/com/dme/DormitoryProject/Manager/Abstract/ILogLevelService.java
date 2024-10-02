package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.entity.LogLevel;

import java.util.List;
import java.util.Optional;

public interface ILogLevelService {
    List<LogLevel> getAll();
    Optional<LogLevel> getById(Long id);
    LogLevel saveLogLevel(LogLevel logLevel);
    LogLevel updateLogLevel(Long id,LogLevel logLevel);
    LogLevel deleteLogLevel(Long id);

}
