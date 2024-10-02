package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IStaffService;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.entity.Staff;
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
    public List<StaffDTO> getAll(){
        return this.staffService.getAll();
    }
    @GetMapping("staffId/{id}")
    public Optional<StaffDTO> getById(@PathVariable Long id){
        return this.staffService.getById(id);
    }
    @PostMapping("saveStaff")
    public Staff saveStaff(@RequestBody  StaffDTO staffDTO){
        return this.staffService.saveStaff(staffDTO);
    }
    @PutMapping("update/{id}")
    public Staff updateStaff(@PathVariable Long id,@RequestBody StaffDTO staffDTO){
        return this.staffService.updateStaff(id,staffDTO);
    }
    @PutMapping("delete/{id}")
    public  Staff deleteStaff(@PathVariable Long id){
        return this.staffService.deleteStaff(id);
    }
}
