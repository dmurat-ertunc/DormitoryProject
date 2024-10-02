package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IStudentService;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentManager implements IStudentService{

    private IStudentDao studentDao;
    private IRentalDao rentalDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    private IUniversityDao universityDao;

    @Autowired
    public StudentManager(IStudentDao studentDao, IRentalDao rentalDao, ILgoDao lgoDao, ILogLevelDao logLevelDao,IUniversityDao universityDao) {
        this.studentDao = studentDao;
        this.rentalDao = rentalDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.universityDao = universityDao;
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
    public List<Student> getAll(){
        LogLevelSave(2,"Tüm öğrenciler listelendi");
        return studentDao.findAll();
    }

    @Override
    public Student findStudentById(Long id){
        LogLevelSave(2,"Id değerine ait öğrenci listelendi");
        return studentDao.findStudentById(id);
    }

    @Override
    public List<Student> findStudentsOlderThan18Native(){
        return studentDao.findStudentsOlderThan18Native();
    }

    @Override
    public Student saveStudent(Student student){
        if(student.getUniversity() == null || student.getMail() == "" || student.getName() == "" || student.getSurName() == "" || student.getTcNo() == "" || student.getBirthDate() == null){
            LogLevelSave(4,"Öğrenci ekleme işleminde boş alan bırakılamaz");
            throw new RuntimeException("hata");
        }
        if(!(student.getTcNo().length() == 11)){
            LogLevelSave(4,"Öğrenci ekleme işleminde TC kimlik numarısını uygun giriniz");
            throw new RuntimeException("hata");
        }


        student.setAddDate(getMomentDate());
        student.setVerify(false);
        LogLevelSave(3,"Öğrenci ekleme işlemi başarılı");
        return studentDao.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student){
        Student editStudent = studentDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir öğrenci bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });

        if(student.getUniversity() == null || student.getMail() == "" || student.getName() == "" || student.getSurName() == "" || student.getTcNo() == "" || student.getBirthDate() == null){
            LogLevelSave(4,"Öğrenci güncelleme işleminde boş alan bırakılamaz");
            throw new RuntimeException("hata");
        }
        if(!(student.getTcNo().length() == 11)){
            LogLevelSave(4,"Öğrenci güncelleme işleminde TC kimlik numarısını uygun giriniz");
            throw new RuntimeException("hata");
        }
        editStudent.setName(student.getName());
        LogLevelSave(3,"Öğrenci güncelleme işlemi başarılı.");
        return studentDao.save(editStudent);
    }

    @Override
    public Student deleteStudent(Long id){
        Student deleteStudent = studentDao.findById(id)
                .orElseThrow(()->{
                    LogLevelSave(1,"Bu id değerine ait bir öğrenci bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        boolean check=false;
        List<Rental> rentalList = rentalDao.findAll();
        for (Rental rental : rentalList) {
            if (rental.getStudent().getId() == id) {
                check= true;
                rental.setDeleted(true);
            }
        }
        if (check){
            LogLevelSave(3,"Öğrenci silindi ve öğrencinin kiraladığı alanlar silindi");
            deleteStudent.setDeleted(true);
            return studentDao.save(deleteStudent);
        }

        LogLevelSave(3,"Öğrenci silme İşlemi başarılı.");
        deleteStudent.setDeleted(true);
        return studentDao.save(deleteStudent);
    }

    @Override
    public List<Student> saveStudentAll(List<Student> students){
        students.forEach(student -> student.setAddDate(getMomentDate()));
        return studentDao.saveAll(students);
    }
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }

}





















