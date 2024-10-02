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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        log.setAddDate(getMomentDate());
        log.setMessage(message);
        lgoDao.save(log);
    }
    public List<UniversityDTO> entityToDto(List<University> universities){
        List<UniversityDTO> universityDTOS = new ArrayList<>();

        for (University university : universities) {
            UniversityDTO dto = UniversityMapper.toDTO(university);
            universityDTOS.add(dto);
        }
        return universityDTOS;
    }

    public University dtoToEntity(UniversityDTO universityDTO){
        return UniversityMapper.toEntity(universityDTO,studentDao);
    }
    @Override
    public List<UniversityDTO> getAll(){
        List<University> universities = universityDao.findAll();
        LogLevelSave(2,"Tüm üniversiteler listelendi");
        return entityToDto(universities);
    }
    @Override
    public Optional<UniversityDTO> getById(Long id){
        List<UniversityDTO> universityDTOS = entityToDto(universityDao.findAll());
        LogLevelSave(2,"Id değerine göre üniversite listelendi.");
        return universityDTOS.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst();
    }
    @Override
    public University saveUniversity(UniversityDTO universityDTO){
        if(universityDTO.getCity() == "" || universityDTO.getMail() == "" || universityDTO.getName() == "" || universityDTO.getPhoneNumber() == ""){
            LogLevelSave(4,"Üniversite ekleme işleminde boş alan bırakılamaz");
            throw  new RuntimeException("hata");
        }
        if(!(universityDTO.getPhoneNumber().length() == 11 && universityDTO.getPhoneNumber().startsWith("0"))){
            LogLevelSave(4,"Üniversite ekleme işleminde telefon numarasını alanını uıygun girin");
            throw  new RuntimeException("hata");
        }

        LogLevelSave(3,"Üniversite ekleme işlemi başarılı");
        return universityDao.save(dtoToEntity(universityDTO));
    }
    @Override
    public University updateUniversity(Long id, UniversityDTO universityDTO) {
        University editUniversity = universityDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir üniversite bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });

        if (universityDTO.getName() == "" || universityDTO.getPhoneNumber() == "" || universityDTO.getCity() == "" || universityDTO.getMail() == ""){
            LogLevelSave(4,"Üniversite güncelleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Üniversite güncelleme işleminde boş alan bırakılamaz. " + id);
        }
        editUniversity.setName(universityDTO.getName());
        editUniversity.setcity(universityDTO.getCity());
        editUniversity.setmail(universityDTO.getMail());
        editUniversity.setphoneNumber(universityDTO.getPhoneNumber());
        LogLevelSave(3,"Üniversite güncelleme işlemi başarılı.");
        return universityDao.save(editUniversity);
    }
    @Override
    public University deleteUniversity(Long id){
        University deleteUniversity = universityDao.findById(id)
                .orElseThrow(()->{
                    LogLevelSave(1,"Bu id değerine ait bir üniversite bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        List<Student> studentList = studentDao.findByUniversityId(id);
        if (studentList != null){
            LogLevelSave(4, "Bu üniversite öğrenci ile ilişkili, siliniemez.");
            throw new RuntimeException("Bu üniversite öğrenci ile ilişkili, silinemez.");
        }
        LogLevelSave(3,"Üniversite silme İşlemi başarılı.");
        deleteUniversity.setDeleted(true);
        return universityDao.save(deleteUniversity);
    }
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }
}
