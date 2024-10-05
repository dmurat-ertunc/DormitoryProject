package com.dme.DormitoryProject.Manager.Concrete;


import com.dme.DormitoryProject.Manager.Abstract.IManagerService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IManagerDao;
import com.dme.DormitoryProject.repository.IStaffDao;
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

    @Autowired
    public ManagerManager(IManagerDao managerDao, ILgoDao logDao,ILogLevelDao logLevelDao,IStaffDao staffDao){
        this.managerDao=managerDao;
        this.logDao=logDao;
        this.logLevelDao=logLevelDao;
        this.staffDao=staffDao;
    }



    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setAddDate(getMomentDate());
        log.setMessage(message);
        logDao.save(log);
    }

    public List<ManagerDTO> entityToDto(List<Manager> managers){
        List<ManagerDTO> managerDTOS = new ArrayList<>();

        for (Manager manager : managers) {
            //ManagerDTO dto = ManagerMapper.toDTO(manager);
            //managerDTOS.add(dto);
        }
        return managerDTOS;
    }

    public Manager dtoToEntity(ManagerDTO managerDTO){
        return ManagerMapper.toEntitiy(managerDTO);
    }

    @Override
    public List<ManagerDTO> getAll(){
        List<Manager> managerList = managerDao.findAll();
        LogLevelSave(2,"Tüm yöneticiller listelendi.");
        return entityToDto(managerList);
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
        if (managerDTO.getName()==null || managerDTO.getPhoneNumber()==null || managerDTO.getSalary() == 0 || managerDTO.getSurName() == null || managerDTO.getTitle()==null){
            LogLevelSave(1,"Yönetici ekleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Hata");
        }
        if(!(managerDTO.getPhoneNumber().length() == 11 && managerDTO.getPhoneNumber().startsWith("0"))){
            LogLevelSave(4,"Telefon numarası alanını uygun girin");
            throw new RuntimeException("hata");
        }
        //List<Manager> managers = managerDao.findAll();
        //for (Manager manager: managers){
        //    if(Objects.equals(manager.getMail(), managerDTO.getMail())){
        //        LogLevelSave(4,"Bu mail hesabına ait yönetici zaten mevcuttur");
        //        throw new RuntimeException("hata");
        //    }
        //}
        //for (Manager manager: managers){
        //    if(Objects.equals(manager.getPhoneNumber(), managerDTO.getPhoneNumber())){
        //        LogLevelSave(4,"Bu telefon numarasına ait yönetici zaten mevcuttur");
        //        throw new RuntimeException("hata");
        //   }
        //}

            LogLevelSave(3,"Yönetici ekleme işlemi başarılı");
        return managerDao.save(dtoToEntity(managerDTO));
    }
    public Manager updateManager(Long id, ManagerDTO managerDTO){
        Manager editManager = managerDao.findById(id)
                        .orElseThrow(()-> {
                            LogLevelSave(1,"Bu id değerine ait bir yönetici bulunamadı.");
                            return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                        });
        List<Manager> managers = managerDao.findAll();
        for (Manager manager: managers){
            if(Objects.equals(manager.getMail(), managerDTO.getMail()) && !Objects.equals(manager.getId(),id)){
                LogLevelSave(4,"Bu mail hesabına ait yönetici zaten mevcuttur");
                throw new RuntimeException("hata");
            }
        }
        for (Manager manager: managers){
            if(Objects.equals(manager.getPhoneNumber(), managerDTO.getPhoneNumber()) && !Objects.equals(manager.getId(),id)){
                LogLevelSave(4,"Bu telefon numarasına ait yönetici zaten mevcuttur");
                throw new RuntimeException("hata");
            }
        }
        if (managerDTO.getName()=="" || managerDTO.getMail()=="" || managerDTO.getPhoneNumber()=="" || managerDTO.getSalary() == 0 || managerDTO.getSurName() == "" || managerDTO.getTitle()==""){
            LogLevelSave(1,"Yönetici güncelleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Hata");
        }
        if (!(managerDTO.getPhoneNumber().length() == 11 || managerDTO.getPhoneNumber().startsWith("0"))) {
            LogLevelSave(1,"Yönetici güncelleme işleminde telefon numarası alanına uygun girin.");
            throw new RuntimeException("Hata");
        }
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
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }
}
