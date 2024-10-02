package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.ITitleService;
import com.dme.DormitoryProject.dtos.titleDtos.TitleDTO;
import com.dme.DormitoryProject.entity.Title;
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
    public List<TitleDTO> getAll(){
        return this.titleService.getAll();
    }
    @GetMapping("titleId/{id}")
    public Optional<TitleDTO> getById(@PathVariable Long id){
        return this.titleService.getById(id);
    }
    @PostMapping("saveTitle")
    public Title saveTitle(@RequestBody TitleDTO titleDTO){
        return this.titleService.saveTitle(titleDTO);
    }
    @PutMapping("update/{id}")
    public Title updateTitle(@RequestBody TitleDTO titleDTO, @PathVariable Long id){
        return this.titleService.updateTitle(id,titleDTO);
    }
    @PutMapping("delete/{id}")
    public Title deleteTitle(@PathVariable Long id){
        return this.titleService.deleteTitle(id);
    }
}
