package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.entity.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface IStudentService {
    Student findStudentById(Long id);
    List<Student> getAll();
    List<Student> findStudentsOlderThan18Native();
    Student saveStudent(Student student);
    List<Student> saveStudentAll(List<Student> students);
    Student updateStudent(Long id,Student student);
    Student deleteStudent(Long id);
}
