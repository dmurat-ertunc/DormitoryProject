package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.ILogLevelService;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.entity.LogLevel;
import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
        log.setAddDate(getMomentDate());
        log.setMessage(message);
        lgoDao.save(log);
    }

    @Override
    public List<LogLevel> getAll(){
        LogLevelSave(2,"Tüm log açıklamaları listelendi");
        return logLevelDao.findAll();
    }

    @Override
    public Optional<LogLevel> getById(Long id){
        Optional<LogLevel> logLevel = logLevelDao.findById(id);
        LogLevelSave(2,"Id değerine göre log açıklaması listelendi");
        return logLevel;
    }

    @Override
    public LogLevel saveLogLevel(LogLevel logLevel){
        if(logLevel.getDescription() == ""){
            LogLevelSave(3,"Log açıklaması tablosunda boşluk bırakılamaz");
            throw new RuntimeException("hata");
        }
        logLevel.setAddDate(getMomentDate());
        LogLevelSave(3,"Log açıklaması tablosuna ekleme işlemi başarılı");
        return logLevelDao.save(logLevel);
    }

    @Override
    public LogLevel updateLogLevel(Long id, LogLevel logLevel){
        LogLevel updateLoglevel = logLevelDao.findById(id)
                .orElseThrow(() -> {
                   LogLevelSave(4,"Bu id değerine ait Log açıklaması bulunamadı.");
                   return new RuntimeException("hata");
                });
        if(logLevel.getDescription() == ""){
            LogLevelSave(1,"Log açıklama tablosunda, güncelleme işlemi için boş alan bırakılamaz");
            throw new RuntimeException("hata");
        }
        updateLoglevel.setDescription(logLevel.getDescription());
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
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }
}
