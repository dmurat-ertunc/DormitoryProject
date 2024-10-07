package com.dme.DormitoryProject.Manager.Concrete;


import com.dme.DormitoryProject.Manager.Abstract.IManagerService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.exception.GlobalExceptionHandler;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IManagerDao;
import com.dme.DormitoryProject.repository.IStaffDao;
import com.dme.DormitoryProject.response.MyResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManagerManager implements IManagerService {
    private IManagerDao managerDao;
    private ILgoDao logDao;
    private ILogLevelDao logLevelDao;
    private IStaffDao staffDao;
    private GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    public ManagerManager(IManagerDao managerDao, ILgoDao logDao,ILogLevelDao logLevelDao,IStaffDao staffDao,GlobalExceptionHandler globalExceptionHandler){
        this.managerDao=managerDao;
        this.logDao=logDao;
        this.logLevelDao=logLevelDao;
        this.staffDao=staffDao;
        this.globalExceptionHandler=globalExceptionHandler;
    }



    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setMessage(message);
        logDao.save(log);
    }

    public List<ManagerDTO> entityToDto(List<Manager> managers){
        List<ManagerDTO> managerDTOS = new ArrayList<>();

        for (Manager manager : managers) {
            ManagerDTO dto = ManagerMapper.toDTO(manager);
            managerDTOS.add(dto);
        }
        return managerDTOS;
    }

    public Manager dtoToEntity(ManagerDTO managerDTO){
        return ManagerMapper.toEntitiy(managerDTO);
    }

    @Override
    public List<ManagerDTO> getAll(){
        try {
            List<Manager> managerList = managerDao.findAll();

        } catch (Exception e) {

        }
    }
    @Override
    public Optional<ManagerDTO> getById(Long id){
        List<ManagerDTO> managerDTOS = entityToDto(managerDao.findAll());
        LogLevelSave(2, "Id değerine göre yönetici listelendi");
        return managerDTOS.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst();
    }

    @Override
    public Manager saveManager(ManagerDTO managerDTO){
        LogLevelSave(3,"Yönetici ekleme işlemi başarılı");
        return managerDao.save(dtoToEntity(managerDTO));
    }
    public Manager updateManager(Long id, ManagerDTO managerDTO){
        Manager editManager = managerDao.findById(id)
                        .orElseThrow(()-> {
                            LogLevelSave(1,"Bu id değerine ait bir yönetici bulunamadı.");
                            //globalExceptionHandler.catchError("id","Bu id değerine ait yönetici bulunamadı");
                            throw new RuntimeException("hata");
                        });
        editManager.setName(managerDTO.getName());
        editManager.setMail(managerDTO.getMail());
        editManager.setPhoneNumber(managerDTO.getPhoneNumber());
        editManager.setSalary(managerDTO.getSalary());
        editManager.setSurName(managerDTO.getSurName());
        editManager.setTitle(managerDTO.getTitle());
        LogLevelSave(3,"Yöentici güncelleme işlemi başarılı");
        return managerDao.save(editManager);
    }
    public Manager deleteManager(Long id){
        Manager deleteManager = managerDao.findById(id)
                .orElseThrow(()->{
                   LogLevelSave(1,"Bu id değerine ait bir yönetici bulunamadı.");
                   return new RuntimeException("hata");
                });
        List<Staff> staffList = staffDao.findAll();  // gireilen id değerini içeren bir staff olup olmadığının kontrolü
        for (Staff staff : staffList) {
            if (staff.getManager().getId() == id) {
                LogLevelSave(4, "Bu manager yönetici ile ilişkili, siliniemez.");
                throw new RuntimeException("Bu manager yönetici ile ilişkili, silinemez.");
            }
        }
        LogLevelSave(3,"Yönetici silme İşlemi başarılı.");
        deleteManager.setDeleted(true);
        return managerDao.save(deleteManager);
    }
}
