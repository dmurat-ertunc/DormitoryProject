package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IStudentService;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.dtos.studentDtos.StudentMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.*;
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

    @Autowired
    public StudentManager(IStudentDao studentDao, IRentalDao rentalDao, ILgoDao lgoDao, ILogLevelDao logLevelDao,IUniversityDao universityDao) {
        this.studentDao = studentDao;
        this.rentalDao = rentalDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.universityDao = universityDao;
    }
    public List<StudentDTO> entityToDto(List<Student> students){
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student : students) {
            StudentDTO dto = StudentMapper.toDto(student);
            studentDTOS.add(dto);
        }
        return studentDTOS;
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
        log.setAddDate(getMomentDate());
        log.setMessage(message);
        lgoDao.save(log);
    }

    @Override
    public List<StudentDTO> getAll(){
        List<Student> studentList = studentDao.findAll();
        LogLevelSave(2,"Tüm öğrenciler listelendi");
        return entityToDto(studentList);
    }

    @Override
    public Optional<StudentDTO> findStudentById(Long id){
        List<StudentDTO> studentDTO = entityToDto(studentDao.findAll());
        LogLevelSave(2,"Id değerine ait öğrenci listelendi");
        return studentDTO.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Student> findStudentsOlderThan18Native(){
        return studentDao.findStudentsOlderThan18Native();
    }

    @Override
    public Student saveStudent(StudentDTO studentDTO){
        List<Student> students = studentDao.findAll();

        if (control(students,studentDTO,"getMail") || control(students,studentDTO,"getTcNo")){
            throw new RuntimeException("hata");
        }
        if(studentDTO.getUniversityIds() == null || studentDTO.getMail() == "" || studentDTO.getName() == "" || studentDTO.getSurName() == "" || studentDTO.getTcNo() == "" || studentDTO.getBirthDate() == null){
            LogLevelSave(4,"Öğrenci ekleme işleminde boş alan bırakılamaz");
            throw new RuntimeException("hata");
        }
        if(!(studentDTO.getTcNo().length() == 11)){
            LogLevelSave(4,"Öğrenci ekleme işleminde TC kimlik numarısını uygun giriniz");
            throw new RuntimeException("hata");
        }
        studentDTO.setVerify(false);
        LogLevelSave(3,"Öğrenci ekleme işlemi başarılı");
        return studentDao.save(dtoToEntity(studentDTO));
    }

    @Override
    public Student updateStudent(Long id, StudentDTO studentDTO){
        Student editStudent = studentDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir öğrenci bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        List<Student> students = studentDao.findAll();
        students.remove(editStudent);
        if (control(students,studentDTO,"getTcNo") ||  control(students,studentDTO,"getMail")){
            throw  new RuntimeException("hata");
        }

        if(studentDTO.getUniversityIds() == null || studentDTO.getMail() == "" || studentDTO.getName() == "" || studentDTO.getSurName() == "" || studentDTO.getTcNo() == "" || studentDTO.getBirthDate() == null){
            LogLevelSave(4,"Öğrenci güncelleme işleminded boş alan bırakılamaz");
            throw new RuntimeException("hata");
        }
        if(!(studentDTO.getTcNo().length() == 11)){
            LogLevelSave(4,"Öğrenci güncelleme işleminde TC kimlik numarısını uygun giriniz");
            throw new RuntimeException("hata");
        }
        editStudent.setName(studentDTO.getName());
        editStudent.setTcNo(studentDTO.getTcNo());
        editStudent.setBirthDate(studentDTO.getBirthDate());
        editStudent.setSurName(studentDTO.getSurName());
        editStudent.setMail(studentDTO.getMail());
        LogLevelSave(3,"Öğrenci güncelleme işlemi başarılı");
            return studentDao.save(editStudent);
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
                    LogLevelSave(4,"Aynı " + metot + "değerine sahip öğrenci bulunda");
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





















