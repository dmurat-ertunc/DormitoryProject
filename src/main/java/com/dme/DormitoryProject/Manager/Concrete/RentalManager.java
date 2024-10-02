package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IRentalService;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.entity.LogLevel;
import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalManager implements IRentalService {

    private IRentalDao rentalDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    private IStudentDao studentDao;
    private ISportAreaDao sportAreaDao;

    @Autowired
    public RentalManager(IRentalDao rentalDao, ILgoDao lgoDao, ILogLevelDao logLevelDao, IStudentDao studentDao, ISportAreaDao sportAreaDao) {
        this.rentalDao = rentalDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.studentDao = studentDao;
        this.sportAreaDao = sportAreaDao;
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
    public List<Rental> getAll(){
        LogLevelSave(3,"Tüm kiralamalar listlendi");
        return rentalDao.findAll();
    }
    @Override
    public Optional<Rental> getById(Long id){
        Optional<Rental> rental = rentalDao.findById(id);
        LogLevelSave(3,"Id değerine göre kiralama listlendi");
        return  rental;
    }
    @Override
    public Rental saveRental(Rental rental){
        if(rental.getSportArea() == null || rental.getStudent() == null || rental.getStartTime() == null || rental.getEndTime() == null || rental.getRentalDate() == null){
            LogLevelSave(1,"Kiralama ekleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Hata");
        }
        if (rental.getSportArea().getIsDeleted() || rental.getStudent().isDeleted()){
            LogLevelSave(1,"Kiralama ekleme işleminde, ilişki olacağı tablo kaldırılmış.");
            throw new RuntimeException("Hata");
        }
        rental.setAddDate(getMomentDate());
        LogLevelSave(3,"Kiralama işlemi başarılı.");
        return rentalDao.save(rental);
    }
    @Override
    public Rental updateRental(Long id, Rental rental){
        Rental editRental = rentalDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir kiralama bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });

        if(rental.getSportArea() == null || rental.getStudent() == null || rental.getStartTime() == null || rental.getEndTime() == null || rental.getRentalDate() == null){
            LogLevelSave(1,"Kiralama güncelleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Hata");
        }
        if (rental.getSportArea().getIsDeleted() || rental.getStudent().isDeleted()){
            LogLevelSave(1,"Kiralama güncelleme işleminde, ilişki olacağı tablo kaldırılmış.");
            throw new RuntimeException("Hata");
        }
        editRental.setEndTime(rental.getEndTime());
        editRental.setRentalDate(rental.getRentalDate());
        editRental.setStartTime(rental.getStartTime());
        editRental.setSportArea(rental.getSportArea());
        editRental.setStudent(rental.getStudent());
        LogLevelSave(3,"Kiralama güncelleme işlemi başarılı.");
        return rentalDao.save(editRental);
    }
    @Override
    public Rental deleteRental(Long id){
        Rental deleteRental = rentalDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir kiralama bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        deleteRental.setDeleted(true);
        LogLevelSave(3,"Kiralama silme işlemi başarılı");
        return rentalDao.save(deleteRental);
    }
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }
}
//"startTime": "14:30:00",
//    "endTime": "18:45:30"
