package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IRentalService;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.dtos.rentalDtos.RentalMapper;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffMapper;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.entity.LogLevel;
import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public List<RentalDTO> entityToDto(List<Rental> rentals){
        List<RentalDTO> rentalDTOS = new ArrayList<>();

        for (Rental rental : rentals) {
            RentalDTO dto = RentalMapper.toDTO(rental);
            rentalDTOS.add(dto);
        }
        return rentalDTOS;
    }

    public Rental dtoToEntity(RentalDTO rentalDTO){
        return RentalMapper.toEntity(rentalDTO,studentDao,sportAreaDao);
    }

    @Override
    public List<RentalDTO> getAll(){
        List<Rental> rentalList = rentalDao.findAll();
        LogLevelSave(3,"Tüm kiralamalar listlendi");
        return entityToDto(rentalList);
    }
    @Override
    public Optional<RentalDTO> getById(Long id){
        List<RentalDTO> rentalDTOS = entityToDto(rentalDao.findAll());
        LogLevelSave(3,"Id değerine göre kiralama listlendi");
        return rentalDTOS.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst();
    }
    @Override
    public Rental saveRental(RentalDTO rentalDTO){
        if(rentalDTO.getSportAreaId() == null || rentalDTO.getStudentId() == null || rentalDTO.getStartTime() == null || rentalDTO.getEndTime() == null || rentalDTO.getRentalDate() == null){
            LogLevelSave(1,"Kiralama ekleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Hata");
        }
        Rental rental = dtoToEntity(rentalDTO);
        if (rental.getSportArea().getIsDeleted() || rental.getStudent().isDeleted()){
            LogLevelSave(1,"Kiralama ekleme işleminde, ilişki olacağı tablo kaldırılmış.");
            throw new RuntimeException("Hata");
        }

        List<Rental> rentals = rentalDao.findAll();
        for(Rental rental1 : rentals){
            if (rentalDTO.getStartTime().isAfter(rental1.getStartTime()) && rentalDTO.getStartTime().isBefore(rental1.getEndTime())) {
                LogLevelSave(1, "Bu alan daha önce kiralanmış");
                throw new RuntimeException("Hata");
            }
        }






        LogLevelSave(3,"Kiralama işlemi başarılı.");
        return rentalDao.save(rental);
    }
    @Override
    public Rental updateRental(Long id, RentalDTO rentalDTO){
        Rental editRental = rentalDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir kiralama bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });

        if(rentalDTO.getSportAreaId() == null || rentalDTO.getStudentId() == null || rentalDTO.getStartTime() == null || rentalDTO.getEndTime() == null || rentalDTO.getRentalDate() == null){
            LogLevelSave(1,"Kiralama güncelleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Hata");
        }
        Rental rental = dtoToEntity(rentalDTO);
        if (rental.getSportArea().getIsDeleted() || rental.getStudent().isDeleted()){
            LogLevelSave(1,"Kiralama güncelleme işleminde, ilişki olacağı tablo kaldırılmış.");
            throw new RuntimeException("Hata");
        }
        editRental.setSportArea(sportAreaDao.getById(rentalDTO.getSportAreaId()));
        editRental.setStudent(studentDao.getById(rentalDTO.getStudentId()));
        editRental.setEndTime(rental.getEndTime());
        editRental.setRentalDate(rental.getRentalDate());
        editRental.setStartTime(rental.getStartTime());
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
