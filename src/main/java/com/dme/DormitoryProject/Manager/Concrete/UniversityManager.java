package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IUniversityService;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IStudentDao;
import com.dme.DormitoryProject.repository.IUniversityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public List<University> getAll(){
        LogLevelSave(2,"Tüm üniversiteler listelendi");
        return universityDao.findAll();
    }
    @Override
    public Optional<University> getById(Long id){
        Optional<University> university=universityDao.findById(id);
        LogLevelSave(2,"Id değerine göre üniversite listelendi.");
        return university;
    }
    @Override
    public University saveUniversity(University university){
        if(university.getcity() == "" || university.getmail() == "" || university.getName() == "" || university.getphoneNumber() == ""){
            LogLevelSave(4,"Üniversite ekleme işleminde boş alan bırakılamaz");
            throw  new RuntimeException("hata");
        }
        if(!(university.getphoneNumber().length() == 11 && university.getphoneNumber().startsWith("0"))){
            LogLevelSave(4,"Üniversite ekleme işleminde telefon numarasını alanını uıygun girin");
            throw  new RuntimeException("hata");
        }
        university.setAddDate(getMomentDate());
        LogLevelSave(3,"Üniversite ekleme işlemi başarılı");
        return universityDao.save(university);
    }
    @Override
    public University updateUniversity(Long id, University university) {
        University editUniversity = universityDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir üniversite bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });

        if (university.getName() == "" || university.getphoneNumber() == "" || university.getcity() == "" || university.getmail() == ""){
            LogLevelSave(4,"Üniversite güncelleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Üniversite güncelleme işleminde boş alan bırakılamaz. " + id);
        }
        editUniversity.setName(university.getName());
        editUniversity.setcity(university.getcity());
        editUniversity.setmail(university.getmail());
        editUniversity.setphoneNumber(university.getphoneNumber());
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
