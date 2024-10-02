package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IDepartmentService;
import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/departments/")
public class DepartmentController {

    private IDepartmentService departmentService;

    @Autowired
    public  DepartmentController(IDepartmentService departmentService){
        this.departmentService=departmentService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<DepartmentDTO>> getAll(){
        List<DepartmentDTO> departments = departmentService.getAll(); // Service'den DTO türünde döndürülüyor
        return ResponseEntity.ok(departments);
    }

    @GetMapping("departmentId/{id}")
    public Optional<DepartmentDTO> getById(@PathVariable Long id){
        return this.departmentService.getById(id);
    }

    @PostMapping("saveDepartment")
    public Department saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        return this.departmentService.saveDepartment(departmentDTO);
    }
    @PutMapping("update/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO ){
        return this.departmentService.updateDepartment(id,departmentDTO);
    }
    @PutMapping("delete/{id}")
    public Department deleteDepartment(@PathVariable Long id){
        return this.departmentService.deleteDepartment(id);
    }



}
