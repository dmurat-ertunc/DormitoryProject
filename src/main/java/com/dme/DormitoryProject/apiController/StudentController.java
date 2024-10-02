package com.dme.DormitoryProject.apiController;


import com.dme.DormitoryProject.Manager.Abstract.IStudentService;
import com.dme.DormitoryProject.Manager.Concrete.StudentManager;
import com.dme.DormitoryProject.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students/")
public class StudentController {

    private IStudentService studentService;

    @Autowired
    public StudentController(IStudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping("getAll")
    public List<Student> getAll(){
        return studentService.getAll();
    }

    @GetMapping("studentId/{id}")
    public Student getById(@PathVariable Long id){
        if (id == null){
            System.out.println("id boş");
        }
        System.out.println("başarılı");
        return this.studentService.findStudentById(id);
    }

    @GetMapping("getUpperTo18")
    public List<Student> findStudentsOlderThan18Native(){
        System.out.println("başarılı");
        return this.studentService.findStudentsOlderThan18Native();
    }

    @PostMapping("saveStudent")
    public Student saveStudent(@RequestBody Student student){
        return this.studentService.saveStudent(student);
    }

    @PostMapping("saveStudentsAll")
    public List<Student> saveStudentAll(@RequestBody List<Student> students) {
        return this.studentService.saveStudentAll(students);
    }
    @PutMapping("update/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){
        return this.studentService.updateStudent(id,student);
    }
    @PutMapping("delete/{id}")
    public Student deleteStudent(@PathVariable Long id){
        return this.studentService.deleteStudent(id);
    }
}
