package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.ITitleService;
import com.dme.DormitoryProject.dtos.titleDtos.TitleDTO;
import com.dme.DormitoryProject.entity.Title;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/titlies/")
public class TitleController {
    private ITitleService titleService;
    @Autowired
    public TitleController(ITitleService titleService){
        this.titleService=titleService;
    }

    @GetMapping("getAll")
    public Result getAll(){
        return this.titleService.getAll();
    }
    @GetMapping("titleId/{id}")
    public Result getById(@PathVariable Long id){
        return this.titleService.getById(id);
    }
    @PostMapping("saveTitle")
    public Result saveTitle(@RequestBody @Valid TitleDTO titleDTO){
        return this.titleService.saveTitle(titleDTO);
    }
    @PutMapping("update/{id}")
    public Result updateTitle(@RequestBody @Valid TitleDTO titleDTO, @PathVariable Long id){
        return this.titleService.updateTitle(id,titleDTO);
    }
    @PutMapping("delete/{id}")
    public Result deleteTitle(@PathVariable Long id){
        return this.titleService.deleteTitle(id);
    }
}
