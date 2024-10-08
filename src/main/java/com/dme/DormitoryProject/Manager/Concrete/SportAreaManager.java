package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.ISporAreaService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerMapper;
import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaDTO;
import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IRentalDao;
import com.dme.DormitoryProject.repository.ISportAreaDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public List<SportAreaDTO> entityToDtoList(List<SportArea> sportAreas){
        List<SportAreaDTO> sportAreaDTOS = new ArrayList<>();

        for (SportArea sportArea : sportAreas) {
            SportAreaDTO dto = SportAreaMapper.toDTO(sportArea);
            sportAreaDTOS.add(dto);
        }
        return sportAreaDTOS;
    }
    public SportAreaDTO entityToDtoObject(SportArea sportArea){
        return SportAreaMapper.toDTO(sportArea);
    }

    public SportArea dtoToEntity(SportAreaDTO sportAreaDTO){
        return SportAreaMapper.toEntity(sportAreaDTO);
    }

    @Override
    public Result getAll(){
        try {
            List<SportAreaDTO> sportAreaDTOS = entityToDtoList(sportAreaDao.findAll());
            LogLevelSave(2,"Spor türleri listelendi");
            return new SuccessDataResult("Spor türleri listelendi",true,sportAreaDTOS);
        } catch (Exception e) {
            LogLevelSave(1,"Spor alanları listelenemedi");
            return new ErrorResult("Spor alanları listelenemedi",false);
        }
    }

    @Override
    public Result getById(Long id){
        try {
            SportAreaDTO sportAreaDTO = entityToDtoObject(sportAreaDao.getById(id));
            LogLevelSave(2,"Id değerine göre spor türü listelendi");
            return new SuccessDataResult("Id değerine göre spor türü listelendi",true,sportAreaDTO);
        } catch (Exception e) {
            LogLevelSave(1,"Spor alanlanı listelenemedi");
            return new ErrorResult("Spor alanı listelenemedi",false);
        }
    }
    @Override
    public Result saveSportArea(SportAreaDTO sportAreaDTO){
        try {
            sportAreaDao.save(dtoToEntity(sportAreaDTO));
            LogLevelSave(3,"Spor türü ekleme işlemi başarılı");
            return new SuccessDataResult("Spor türü ekleme işlemi başarılı",true,sportAreaDTO);
        } catch (Exception e) {
            LogLevelSave(1,"Spor türü ekleme işlemi başarısız");
            return new ErrorResult("Spor türü ekleme işlemi başarısız",false);
        }
    }
    @Override
    public Result updateSportArea(Long id,SportAreaDTO sportAreaDTO){
        SportArea editSportArea;
        try {
            editSportArea = sportAreaDao.getById(id);
            editSportArea.setSporType(sportAreaDTO.getSportType());
            sportAreaDao.save(editSportArea);
            LogLevelSave(3,"Spor alanı güncelleme işlemi başarılı");
            return new SuccessDataResult("Spor alanı güncelleme  işlemi başarılı",true,entityToDtoObject(editSportArea));
        }catch (Exception e){
            LogLevelSave(1,"Bu id değerine ait bir spor alanı bulunamadı");
            return new ErrorResult("Bu id değerine ait bir spor alanı bulunamadı",false);
        }
    }
    @Override
    public Result deleteSportArea(Long id){
        SportArea deleteSportArea;
        try {
            deleteSportArea = sportAreaDao.getById(id);
            List<Rental> rentalList = rentalDao.findAll();  // gireilen id değerini içeren bir staff olup olmadığının kontrolü
            for (Rental rental : rentalList) {
                if (rental.getSportArea().getId() == id) {
                    LogLevelSave(4, "Bu spor alanı, kiralama ile ilişkili, siliniemez.");
                    return new ErrorResult("Bu spor alanı, kiralama ile ilişkili, siliniemez.",false);
                }
            }
            deleteSportArea.setDeleted(true);
            sportAreaDao.save(deleteSportArea);
            LogLevelSave(3,"Spor alanı silme İşlemi başarılı.");
            return new SuccesResult("Spor alanı silme işlemi başarılı",true);
        } catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Bu id değerine ait bir spor alanı bulunamadı.");
            return new ErrorResult("Bu id değerinde spor alanı bulunamadı",false);
        }
    }
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }

}
