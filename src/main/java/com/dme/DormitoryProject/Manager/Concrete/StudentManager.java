package com.dme.DormitoryProject.Manager.Concrete;

import ch.qos.logback.classic.spi.LoggerContextListener;
import com.dme.DormitoryProject.Manager.Abstract.IMailService;
import com.dme.DormitoryProject.Manager.Abstract.IRedisService;
import com.dme.DormitoryProject.Manager.Abstract.IStudentService;
import com.dme.DormitoryProject.dtos.mailVerification.MailVerificationDTO;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.dtos.studentDtos.StudentMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.*;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;

@Service
public class StudentManager implements IStudentService{

    private IStudentDao studentDao;
    private IRentalDao rentalDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    private IUniversityDao universityDao;
    private IRedisService redisService;
    private IMailService mailService;

    @Autowired
    public StudentManager(IStudentDao studentDao, IRentalDao rentalDao, ILgoDao lgoDao, ILogLevelDao logLevelDao,IUniversityDao universityDao,IRedisService redisService, IMailService mailService) {
        this.studentDao = studentDao;
        this.rentalDao = rentalDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.universityDao = universityDao;
        this.redisService=redisService;
        this.mailService=mailService;
    }
    public List<StudentDTO> entityToDtoList(List<Student> students){
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student : students) {
            StudentDTO dto = StudentMapper.toDto(student);
            studentDTOS.add(dto);
        }
        return studentDTOS;
    }

    public StudentDTO entityToDtoObject(Student student){
        return StudentMapper.toDto(student);
    }

    public Student dtoToEntity(StudentDTO studentDTO){
        return StudentMapper.toEntity(studentDTO,universityDao);
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

    @Override
    public Result getAll(){
        try {
            List<StudentDTO> studentDTOS = entityToDtoList(studentDao.findAll());
            LogLevelSave(2,"Tüm öğrenciler listelendi");
            return new SuccessDataResult("Tüm öğrenciler listelendi",true,studentDTOS);
        }catch (Exception e){
            LogLevelSave(1,"Tüm öğrencilerin listelenmesinde bir hata oluştu");
            return new ErrorResult("Tüm öğrenciler listelenirken hata oluştu",false);
        }
    }

    @Override
    public Result findStudentById(Long id){
        try{
            StudentDTO studentDTO = entityToDtoObject(studentDao.getById(id));
            LogLevelSave(2,"İd değerine göre öğrenci listelendi");

            return new SuccessDataResult("İd değerine göre öğrenci listelendi",true,studentDTO);
        }catch (Exception e){
            LogLevelSave(1,"İd değerine göre öğrenci bulunamadı");
            return new ErrorResult("İd değerine göre öğrenci bulunamadı",false);
        }
    }

    @Override
    public List<Student> findStudentsOlderThan18Native(){
        return studentDao.findStudentsOlderThan18Native();
    }

    @Override
    public Result saveStudent(StudentDTO studentDTO){
        List<Student> students = studentDao.findAll();
        if (control(students,studentDTO,"getMail") || control(students,studentDTO,"getTcNo")){
            LogLevelSave(1,"Mail veya kimlik nummarası benzersiz olmalıdır");
            return new ErrorResult("Mail veya kimlik numarası benzersiz olmaıl",false);
        }
        try {
            studentDTO.setVerify(false);
            studentDao.save(dtoToEntity(studentDTO));
            LogLevelSave(3,"Öğrenci ekleme işlemi başarılı");
            return new SuccessDataResult("Öğrenci ekleme işlemi başarılı",true,studentDTO);
        }catch (Exception e){
            LogLevelSave(1,"Öğrenci ekleme işleminde hata oluştu");
            return new ErrorResult("Öğrenci ekleme  işleminde hata oluştu",false);
        }
    }

    @Override
    public Result sendMail(Long id){
        Student student = studentDao.getById(id);
        redisService.setData(id);
        mailService.sendMail(student.getMail(), redisService.getData(id));
        return new SuccesResult("Mail adresine doğrulama kodu gönderildi",true);
    }

    @Override
    public Result mailVerification(Long id, String mailCode){
        Student student = studentDao.getById(id);
        if(redisService.getData(id) == Integer.parseInt(mailCode)){
            student.setVerify(true);
            studentDao.save(student);
            return new SuccesResult("Doğrulama işlemi başarılı",true);
        }
        return new ErrorResult("Doğrulama işlemi başarısız",false);
    }

    @Override
    public Result findUniversityId(Long id){
        List<Student> students = studentDao.findByUniversity_Id(id);
        if (students !=  null && !students.isEmpty()){
            LogLevelSave(3, "Girilen üniversitede eğitim alan öğrenciler listelendi");
            return new SuccessDataResult("Girilen üniversitede eğitim alan öğrenciler listelendi",true,entityToDtoList(students));
        }
        LogLevelSave(1,"Bu üniversiteye ait öğrenci bulunamadı");
        return new ErrorResult("Bu üniversiteye ait öğrenci bulunamadı",false);
    }

    @Override
    public List<Student> saveStudentAll(List<Student> students){
        return studentDao.saveAll(students);
    }

    @Override
    public Result updateStudent(Long id, StudentDTO studentDTO){

        try {
            Student editStudent = studentDao.getById(id);
            List<Student> students = studentDao.findAll();
            students.remove(editStudent);
            if (control(students,studentDTO,"getTcNo") ||  control(students,studentDTO,"getMail")){
                LogLevelSave(1,"Mail veya kimlik nummarası benzersiz olmalıdır");
                return new ErrorResult("Mail veya kimlik numarası benzersiz olmaıl",false);
            }
            editStudent.setName(studentDTO.getName());
            editStudent.setTcNo(studentDTO.getTcNo());
            editStudent.setBirthDate(studentDTO.getBirthDate());
            editStudent.setSurName(studentDTO.getSurName());
            editStudent.setMail(studentDTO.getMail());
            studentDao.save(editStudent);
            LogLevelSave(3,"Öğrenci güncelleme işlemi başarılı");
            return new SuccessDataResult("Öğrenci güncelleme işlemi başarılı", false,entityToDtoObject(editStudent));
        } catch (Exception e) {
            LogLevelSave(1,"Bu id değerine ait bir öğrenci bulunamadı.");
            return new ErrorResult("Bu id değerine göre öğrenci bulunamadı",false);
        }
    }

    @Override
    public Result deleteStudent(Long id){
        try {
            Student deleteStudent = studentDao.getById(id);
            boolean check=false;
            List<Rental> rentalList = rentalDao.findAll();
            for (Rental rental : rentalList) {
                if (rental.getStudent().getId() == id) {
                    check= true;
                    rental.setDeleted(true);
                }
            }
            if (check){
                deleteStudent.setDeleted(true);
                studentDao.save(deleteStudent);
                LogLevelSave(3,"Öğrenci silindi ve öğrencinin kiraladığı alanlar silindi");
                return new SuccesResult("Öğrenci silindi ve öğrencinin kiraladığı alanlar silindi",true);
            }
            deleteStudent.setDeleted(true);
            studentDao.save(deleteStudent);
            LogLevelSave(3,"Öğrenci silme İşlemi başarılı.");
            return new SuccesResult("Öğrenci silindi",true);
        } catch (Exception e) {
            LogLevelSave(1,"Bu id değerine ait bir öğrenci bulunamadı.");
            return new ErrorResult("Bu id değerine göre öğrenci bulunamadı",false);
        }
    }

    public boolean control(List<Student> students,StudentDTO studentDTO,String metot){
        try {
            // StudentDTO nesnesindeki ilgili metodu çağırarak değeri al
            Method dtoMethod = studentDTO.getClass().getMethod(metot);
            Object dtoValue = dtoMethod.invoke(studentDTO);
            for (Student student : students) {
                Method studentMethod = student.getClass().getMethod(metot);
                Object studentValue = studentMethod.invoke(student);
                if (studentValue.equals(dtoValue)) {
                    LogLevelSave(1,"Aynı " + metot + "değerine sahip öğrenci bulunda");
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            System.err.println("Belirtilen metot bulunamadı: " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }


}





















