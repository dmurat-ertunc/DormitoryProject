package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IUniversityService;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffMapper;
import com.dme.DormitoryProject.dtos.universityDtos.UniversityDTO;
import com.dme.DormitoryProject.dtos.universityDtos.UniversityMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.repository.IUniversityDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UniversityManager implements IUniversityService {

    private IUniversityDao universityDao;
    private IStudentDao studentDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    @Autowired
    public UniversityManager(IUniversityDao universityDao, IStudentDao studentDao, ILgoDao lgoDao, ILogLevelDao logLevelDao) {
        this.universityDao = universityDao;
        this.studentDao = studentDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
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
    public List<UniversityDTO> entityToDtoList(List<University> universities){
        List<UniversityDTO> universityDTOS = new ArrayList<>();

        for (University university : universities) {
            UniversityDTO dto = UniversityMapper.toDTO(university);
            universityDTOS.add(dto);
        }
        return universityDTOS;
    }
    public UniversityDTO entityToDtoObject(University university){
        return UniversityMapper.toDTO(university);
    }

    public University dtoToEntity(UniversityDTO universityDTO){
        return UniversityMapper.toEntity(universityDTO,studentDao);
    }
    @Override
    public Result getAll(){
       try {
           List<UniversityDTO> universityDTOS = entityToDtoList(universityDao.findAll());
           LogLevelSave(2,"Tüm üniversitler listelendi");
           return new SuccessDataResult("Tüm üniversiteler listelendi",true,universityDTOS);
       }catch (Exception e){
           LogLevelSave(1,"Üniversiteler listelenirken hata oluştu.");
           return new ErrorResult("Üniversiteler listeleirken hata oluştu",false);
       }
    }
    @Override
    public Result getById(Long id){
        try{
            UniversityDTO universityDTO = entityToDtoObject(universityDao.getById(id));
            LogLevelSave(2,"İd değerine göre üniversite listelendi");
            return new SuccessDataResult("İd değerine göre üniversite listelendi",true,universityDTO);
        } catch (Exception e) {
            LogLevelSave(1,"İd değerine göre üniversite listelenirken hata oluştu");
            return new ErrorResult("İd değerine göre üniversite listelenirken hata oluştu",false);
        }
    }
    @Override
    public Result saveUniversity(UniversityDTO universityDTO){
        try{
            universityDao.save(dtoToEntity(universityDTO));
            LogLevelSave(3,"Üniversite kaydedildi");
            return new SuccessDataResult("Üniversite kaydedildi",true,universityDTO);
        }catch (Exception e){
            LogLevelSave(1,"Üniversite kaydedilemedi");
            return new ErrorResult("Üniversitesi kaydedilemedi",false);
        }
    }
    @Override
    public Result updateUniversity(Long id, UniversityDTO universityDTO) {

        try {
            University editUniversity = universityDao.getById(id);
            editUniversity.setName(universityDTO.getName());
            editUniversity.setcity(universityDTO.getCity());
            editUniversity.setmail(universityDTO.getMail());
            editUniversity.setphoneNumber(universityDTO.getPhoneNumber());
            LogLevelSave(3,"Üniversite güncelleme işlemi başarılı.");
            return new SuccessDataResult("Üniversite güncelleme işlemi başarılı",true,entityToDtoObject(editUniversity));
        } catch (Exception e) {
            LogLevelSave(1,"Bu id değerine ait bir üniversite bulunamadı");
            return new ErrorResult("Bu id değerine ait bir üniversite bulunamadı",false);
        }
    }
    @Override
    public Result deleteUniversity(Long id){
        List<Student> studentList = studentDao.findByUniversityId(id);
        if (!studentList.isEmpty()){
            LogLevelSave(1, "Bu üniversite öğrenci ile ilişkili, siliniemez.");
            return new ErrorResult("Bu üniversite öğrenci ile ilişkili, silinemez.",false);
        }
        try {
            University deleteUniversity = universityDao.getById(id);
            deleteUniversity.setDeleted(true);
            universityDao.save(deleteUniversity);
            LogLevelSave(3,"Üniversite silme İşlemi başarılı.");
            return new SuccesResult("Üniversite silme işlemi başarlı",true);
        } catch (Exception e) {
            LogLevelSave(1,"Bu id değerine göre üniversite bulunamadı");
            return new ErrorResult("Bu id değerine göre üniversite bulunamadı",false);
        }
    }
}
