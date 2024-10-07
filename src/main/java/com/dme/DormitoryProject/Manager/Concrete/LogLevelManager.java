package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.ILogLevelService;
import com.dme.DormitoryProject.dtos.logLevelDtos.LogLevelDTO;
import com.dme.DormitoryProject.dtos.logLevelDtos.LogLevelMapper;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerMapper;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.entity.LogLevel;
import com.dme.DormitoryProject.entity.Manager;
import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LogLevelManager implements ILogLevelService {

    private ILogLevelDao logLevelDao;
    private ILgoDao lgoDao;

    @Autowired
    public LogLevelManager(ILogLevelDao logLevelDao,ILgoDao lgoDao){
        this.logLevelDao=logLevelDao;
        this.lgoDao=lgoDao;
    }

    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setMessage(message);
        lgoDao.save(log);
    }
    public List<LogLevelDTO> entityToDto(List<LogLevel> logLevels){
        List<LogLevelDTO> logLevelDTOS = new ArrayList<>();

        for (LogLevel logLevel : logLevels) {
            LogLevelDTO dto = LogLevelMapper.toDTO(logLevel);
            logLevelDTOS.add(dto);
        }
        return logLevelDTOS;
    }

    public LogLevel dtoToEntity(LogLevelDTO logLevelDTO){
        return LogLevelMapper.toEntity(logLevelDTO);
    }
    @Override
    public List<LogLevelDTO> getAll(){
        List<LogLevel> logLevel = logLevelDao.findAll();
        LogLevelSave(2,"Tüm log açıklamaları listelendi");
        return entityToDto(logLevel);
    }

    @Override
    public Optional<LogLevelDTO> getById(Long id){
        List<LogLevelDTO> logLevelDTOS = entityToDto(logLevelDao.findAll());
        LogLevelSave(2, "Id değerine göre yönetici listelendi");
        return logLevelDTOS.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst();
        }

    @Override
    public LogLevel saveLogLevel(LogLevelDTO logLevelDTO){
        LogLevelSave(3,"Log açıklaması tablosuna ekleme işlemi başarılı");
        return logLevelDao.save(dtoToEntity(logLevelDTO));
    }

    @Override
    public LogLevel updateLogLevel(Long id, LogLevelDTO logLevelDTO){
        LogLevel updateLoglevel = logLevelDao.findById(id)
                .orElseThrow(() -> {
                   LogLevelSave(4,"Bu id değerine ait Log açıklaması bulunamadı.");
                   return new RuntimeException("hata");
                });
        updateLoglevel.setDescription(logLevelDTO.getDescription());
        LogLevelSave(3,"Log açıklaması tablosunda güncelleme başarılı");
        return logLevelDao.save(updateLoglevel);
    }

    @Override
    public LogLevel deleteLogLevel(Long id)
    {
        LogLevel deleteLogLevel = logLevelDao.findById(id)
                .orElseThrow(() -> {
                    LogLevelSave(4,"Bu id değerine ait Log açıklaması bulunamadı.");
                    return new RuntimeException("hata");
                });
        List<Lgo> lgoList = lgoDao.findAll();
        for (Lgo searchLog : lgoList) {
            if (searchLog.getLogLevel().getId() == id) {
                LogLevelSave(4, "Bu log açıklaması log ile ilişkili, siliniemez.");
                throw new RuntimeException("Bu log açıklaması log ile ilişkili, siliniemez.");
            }
        }
        LogLevelSave(3,"Log açıklaması silme İşlemi başarılı.");
        deleteLogLevel.setDeleted(true);
        return logLevelDao.save(deleteLogLevel);
    }

}
