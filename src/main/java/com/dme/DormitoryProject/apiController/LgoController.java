package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.ILgoService;
import com.dme.DormitoryProject.dtos.lgoDtos.LgoDTO;
import com.dme.DormitoryProject.entity.Lgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lgo/")
public class LgoController {
    private ILgoService lgoService;
    @Autowired
    public LgoController(ILgoService lgoService){
        this.lgoService=lgoService;
    }

    @GetMapping("getAll")
    public List<LgoDTO> getAll(){
        return this.lgoService.getAll();
    }

    @GetMapping("lgoId/{id}")
    public Lgo getById(@PathVariable Long id){
        return this.lgoService.getById(id);
    }
}
