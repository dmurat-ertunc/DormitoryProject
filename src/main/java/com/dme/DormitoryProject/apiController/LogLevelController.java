package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.ILogLevelService;
import com.dme.DormitoryProject.dtos.logLevelDtos.LogLevelDTO;
import com.dme.DormitoryProject.entity.LogLevel;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/logLevels/")
public class LogLevelController {

    private ILogLevelService logLevelService;
    @Autowired
    public LogLevelController(ILogLevelService logLevelService){
        this.logLevelService=logLevelService;
    }

    @GetMapping("getAll")
    public List<LogLevelDTO> getAll(){
        return this.logLevelService.getAll();
    }

    @GetMapping("logLevelId/{id}")
    public Optional<LogLevelDTO> getById(@PathVariable Long id){
        return this.logLevelService.getById(id);
    }

    @PostMapping("saveLogLevel")
    public LogLevel saveLogLevel(@RequestBody LogLevelDTO logLevelDTO){
        return this.logLevelService.saveLogLevel(logLevelDTO);
    }
    @PutMapping("update/{id}")
    public LogLevel updateLogLevel(@PathVariable Long id, @RequestBody LogLevelDTO logLevelDTO){
        return this.logLevelService.updateLogLevel(id,logLevelDTO);
    }
    @PutMapping("delete/{id}")
    public LogLevel deleteLogLevel(@PathVariable Long id){
        return this.logLevelService.deleteLogLevel(id);
    }

}
