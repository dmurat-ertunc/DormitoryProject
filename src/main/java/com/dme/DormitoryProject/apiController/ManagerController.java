package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IManagerService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/managers/")
public class ManagerController {

    private IManagerService managerService;

    @Autowired
    public ManagerController(IManagerService managerService){
        this.managerService=managerService;
    }

    @GetMapping("getAll")
    public List<ManagerDTO> getAll(){
        return this.managerService.getAll();
    }

    @GetMapping("managerId/{id}")
    public Optional<ManagerDTO> getById(@PathVariable Long id){
        return this.managerService.getById(id);
    }
    @PostMapping("saveManager")
    public Manager saveManager(@RequestBody ManagerDTO managerDTO){
        return this.managerService.saveManager(managerDTO);
    }
    @PutMapping("update/{id}")
    public Manager updateManager(@PathVariable Long id,@RequestBody ManagerDTO managerDTO){
        return this.managerService.updateManager(id,managerDTO);
    }
    @PutMapping("delete/{id}")
    public Manager deleteManager(@PathVariable Long id){
        return this.managerService.deleteManager(id);
    }
}
