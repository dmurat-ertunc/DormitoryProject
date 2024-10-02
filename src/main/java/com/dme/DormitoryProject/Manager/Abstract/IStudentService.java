package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.entity.Student;

import java.util.List;
import java.util.Optional;


public interface IStudentService {
    Optional<StudentDTO> findStudentById(Long id);
    List<StudentDTO> getAll();
    List<Student> findStudentsOlderThan18Native();
    Student saveStudent(StudentDTO studentDTO);
    List<Student> saveStudentAll(List<Student> students);
    Student updateStudent(Long id,StudentDTO studentDTO);
    Student deleteStudent(Long id);
}
