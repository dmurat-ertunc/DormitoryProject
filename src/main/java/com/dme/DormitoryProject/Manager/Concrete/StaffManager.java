package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IStaffService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerMapper;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.*;
import org.apache.catalina.startup.ListenerCreateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.util.*;

@Service
public class StaffManager implements IStaffService {

    private IStaffDao staffDao;
    private ITitleDao titleDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;
    private IDepartmentDao departmentDao;
    private IManagerDao managerDao;

    @Autowired
    public StaffManager(IStaffDao staffDao, ILgoDao lgoDao, ILogLevelDao logLevelDao, IDepartmentDao departmentDao, IManagerDao managerDao, ITitleDao titleDao) {
        this.staffDao = staffDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
        this.departmentDao = departmentDao;
        this.managerDao = managerDao;
        this.titleDao = titleDao;
    }

    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setAddDate(getMomentDate());
        log.setMessage(message);
        lgoDao.save(log);
    }
    public List<StaffDTO> entityToDto(List<Staff> staffs){
        List<StaffDTO> staffDTOS = new ArrayList<>();

        for (Staff staff : staffs) {
            StaffDTO dto = StaffMapper.toDTO(staff);
            staffDTOS.add(dto);
        }
        return staffDTOS;
    }

    public Staff dtoToEntity(StaffDTO staffDTO){
        return StaffMapper.toEntity(staffDTO,departmentDao,managerDao,titleDao);
    }

    @Override
    public List<StaffDTO> getAll(){
        List<Staff> staffList = staffDao.findAll();
        LogLevelSave(2,"Tüm personeller listelendi");
        return entityToDto(staffList);
    }
    @Override
    public Optional<StaffDTO> getById(Long id){
        List<StaffDTO> staffDTOS = entityToDto(staffDao.findAll());
        LogLevelSave(2,"Id değerine göre personel listelendi");
        return staffDTOS.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst();
    }
    @Override
    public Staff saveStaff(StaffDTO staffDTO){
        if (staffDTO.getName()=="" || staffDTO.getMail()=="" || staffDTO.getPhoneNumber()=="" || staffDTO.getSalary() == 0
                || staffDTO.getSurName() == "" || staffDTO.getDepartmentId() == null || staffDTO.getTitleId() == null || staffDTO.getManagerId() == null){
            LogLevelSave(1,"Personel ekleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Hata");
        }
        if (!(staffDTO.getPhoneNumber().length() == 11 && staffDTO.getPhoneNumber().startsWith("0"))) {
            LogLevelSave(1,"Personel ekleme işleminde telefon numarası alanına uygun girin.");
            throw new RuntimeException("Hata");
        }
        Staff staff = dtoToEntity(staffDTO);
        // staffların bağlanacığı tablolar var mı yok mu kontrolü için dto entitiye çevrildi
        if (staff.getDepartment().getIsDeleted() || staff.getTitle().getIsDeleted() || staff.getManager().getIsDeleted()){
            LogLevelSave(1,"Personel ekleme işleminde, ilişki olacağı tablo kaldırılmış.");
            throw new RuntimeException("Hata");
        }
        LogLevelSave(3,"Personel ekleme işlemi başarılı.");
        return staffDao.save(staff);
    }

    @Override
    public Staff updateStaff(Long id, StaffDTO staffDTO){
        Staff editStaff = staffDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir personel bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });

        if (Objects.equals(staffDTO.getName(), "") || Objects.equals(staffDTO.getMail(), "") || Objects.equals(staffDTO.getPhoneNumber(), "") || staffDTO.getSalary() == 0
                || Objects.equals(staffDTO.getSurName(), "")){
            LogLevelSave(1,"Personel güncelleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Hata");
        }

        if (!(staffDTO.getPhoneNumber().length() == 11 && staffDTO.getPhoneNumber().startsWith("0"))) {
            LogLevelSave(1,"Personel güncelleme işleminde telefon numarası alanına uygun girin.");
            throw new RuntimeException("Hata");
        }

        Staff staff = dtoToEntity(staffDTO);
        if (staff.getDepartment().getIsDeleted() || staff.getTitle().getIsDeleted() || staff.getManager().getIsDeleted()){
            LogLevelSave(1,"Personel güncelleme işleminde, ilişki olacağı tablo kaldırılmış.");
            throw new RuntimeException("Hata");
        }
        editStaff.setName(staffDTO.getName());
        editStaff.setMail(staffDTO.getMail());
        editStaff.setPhoneNumber(staffDTO.getPhoneNumber());
        editStaff.setSalary(staffDTO.getSalary());
        editStaff.setSurName(staffDTO.getSurName());
        editStaff.setDepartment(departmentDao.getById(staffDTO.getDepartmentId()));
        editStaff.setManager(managerDao.getById(staffDTO.getManagerId()));
        editStaff.setTitle(titleDao.getById(staffDTO.getTitleId()));
        LogLevelSave(3,"Personel güncelleme işlemi başarılı.");
        return staffDao.save(editStaff);
    }
    @Override
    public Staff deleteStaff(Long id){
        Staff deleteStaff = staffDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir personel bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        deleteStaff.setDeleted(true);
        LogLevelSave(3,"Personel silme işlemi başarılı");
        return staffDao.save(deleteStaff);
    }
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }
}
