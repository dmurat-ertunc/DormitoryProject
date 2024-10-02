package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.ISporAreaService;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IRentalDao;
import com.dme.DormitoryProject.repository.ISportAreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SportAreaManager implements ISporAreaService {

    private ISportAreaDao sportAreaDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    private IRentalDao rentalDao;

    @Autowired
    public SportAreaManager(ISportAreaDao sportAreaDao, ILgoDao lgoDao, ILogLevelDao logLevelDao, IRentalDao rentalDao) {
        this.sportAreaDao = sportAreaDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.rentalDao = rentalDao;
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
    public List<SportArea> getAll(){
        LogLevelSave(2,"Tüm spor alanları listelendi");
        return sportAreaDao.findAll();
    }

    @Override
    public Optional<SportArea> getById(Long id){
        Optional<SportArea> sportArea = sportAreaDao.findById(id);
        LogLevelSave(2,"Id değeri verilen spor alanı listelendi");
        return sportArea;
    }
    @Override
    public SportArea saveSportArea(SportArea sportArea){
        if(sportArea.getSporType() == null){
            LogLevelSave(4,"Spor alanı eklerken boş alan bırakılamaz");
            throw new RuntimeException("hata");
        }
        sportArea.setSporType(sportArea.getSporType());
        sportArea.setAddDate(getMomentDate());
        LogLevelSave(2,"Spor alanı eklendi.");
        return sportAreaDao.save(sportArea);
    }
    @Override
    public SportArea updateSportArea(Long id,SportArea sportArea){
        SportArea editSportArea = sportAreaDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir spor alanı bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });

        if(sportArea.getSporType().toString() == ""){
            LogLevelSave(4,"Spor alanı güncellerken boş alan bırakılamaz");
            throw new RuntimeException("hata");
        }
        editSportArea.setSporType(sportArea.getSporType());
        editSportArea.setAddDate(getMomentDate());
        LogLevelSave(3,"Spor alanı güncelleme işlemi başarılı");
        return sportAreaDao.save(editSportArea);
    }
    @Override
    public SportArea deleteSportArea(Long id){
        SportArea deleteSportArea = sportAreaDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir spor alanı bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        List<Rental> rentalList = rentalDao.findAll();
        for (Rental rental : rentalList){
            if(rental.getSportArea().getId() == id){
                LogLevelSave(1,"Bu spor alanı önceden kiralanmış silinemez.");
                throw  new RuntimeException("hata");
            }
        }
        LogLevelSave(3,"Spor Alanı silme İşlemi başarılı.");
        deleteSportArea.setDeleted(true);
        return sportAreaDao.save(deleteSportArea);
    }
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }

}
