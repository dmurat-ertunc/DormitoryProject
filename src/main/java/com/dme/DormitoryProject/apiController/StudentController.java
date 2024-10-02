package com.dme.DormitoryProject.apiController;


import com.dme.DormitoryProject.Manager.Abstract.IStudentService;
import com.dme.DormitoryProject.Manager.Concrete.StudentManager;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/students/")
public class StudentController {

    private IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping("getAll")
    public List<StudentDTO> getAll(){
        return studentService.getAll();
    }

    @GetMapping("studentId/{id}")
    public Optional<StudentDTO> getById(@PathVariable Long id){
        return this.studentService.findStudentById(id);
    }

    @GetMapping("getUpperTo18")
    public List<Student> findStudentsOlderThan18Native(){
        System.out.println("başarılı");
        return this.studentService.findStudentsOlderThan18Native();
    }

    @PostMapping("saveStudent")
    public Student saveStudent(@RequestBody StudentDTO studentDTO){
        return this.studentService.saveStudent(studentDTO);
    }

    @PostMapping("saveStudentsAll")
    public List<Student> saveStudentAll(@RequestBody List<Student> students) {
        return this.studentService.saveStudentAll(students);
    }
    @PutMapping("update/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return this.studentService.updateStudent(id,studentDTO);
    }
    @PutMapping("delete/{id}")
    public Student deleteStudent(@PathVariable Long id){
        return this.studentService.deleteStudent(id);
    }
}
