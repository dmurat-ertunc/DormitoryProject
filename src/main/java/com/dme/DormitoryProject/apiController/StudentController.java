package com.dme.DormitoryProject.apiController;


import com.dme.DormitoryProject.Manager.Abstract.IStudentService;
import com.dme.DormitoryProject.Manager.Concrete.StudentManager;
import com.dme.DormitoryProject.dtos.mailVerification.MailVerificationDTO;
import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
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
    public Result getAll(){
        return studentService.getAll();
    }

    @GetMapping("studentId/{id}")
    public Result getById(@PathVariable Long id){
        return this.studentService.findStudentById(id);
    }

    @GetMapping("getUpperTo18")
    public List<Student> findStudentsOlderThan18Native(){
        System.out.println("başarılı");
        return this.studentService.findStudentsOlderThan18Native();
    }

    @PostMapping("saveStudent")
    public Result saveStudent(@RequestBody @Valid StudentDTO studentDTO){
        return this.studentService.saveStudent(studentDTO);
    }
    @GetMapping("sendMail/{id}")
    public Result sendMail(@PathVariable Long id){
        return this.studentService.sendMail(id);
    }

    @PutMapping("mailVerification/{id}")
    public Result mailVerification(@PathVariable Long id,@RequestParam String mailCode){
        return this.studentService.mailVerification(id,mailCode);
    }

    @GetMapping("findByUniversityId/{id}")
    public Result findByUniversityId(@PathVariable Long id){
        return this.studentService.findUniversityId(id);
    }

    @PostMapping("saveStudentsAll")
    public List<Student> saveStudentAll(@RequestBody List<Student> students) {
        return this.studentService.saveStudentAll(students);
    }
    @PutMapping("update/{id}")
    public Result updateStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO){
        return this.studentService.updateStudent(id,studentDTO);
    }
    @PutMapping("delete/{id}")
    public Result deleteStudent(@PathVariable Long id){
        return this.studentService.deleteStudent(id);
    }


}
