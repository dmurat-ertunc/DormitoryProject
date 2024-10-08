package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IStaffService;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/staffs/")
public class StaffController {
    private IStaffService staffService;
    @Autowired
    public StaffController(IStaffService staffService){
        this.staffService=staffService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.staffService.getAll();
    }
    @GetMapping("staffId/{id}")
    public Result getById(@PathVariable Long id){
        return this.staffService.getById(id);
    }
    @PostMapping("saveStaff")
    public Result saveStaff(@RequestBody @Valid StaffDTO staffDTO){
        return this.staffService.saveStaff(staffDTO);
    }
    @PutMapping("update/{id}")
    public Result updateStaff(@PathVariable Long id,@RequestBody @Valid StaffDTO staffDTO){
        return this.staffService.updateStaff(id,staffDTO);
    }
    @PutMapping("delete/{id}")
    public  Result deleteStaff(@PathVariable Long id){
        return this.staffService.deleteStaff(id);
    }
}
