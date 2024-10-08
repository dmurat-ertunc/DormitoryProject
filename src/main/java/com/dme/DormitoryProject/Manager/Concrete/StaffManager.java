package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IStaffService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerMapper;
import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.dtos.staffDtos.StaffMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.*;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.apache.catalina.startup.ListenerCreateRule;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        log.setMessage(message);
        lgoDao.save(log);
    }
    public List<StaffDTO> entityToDtoList(List<Staff> staffs){
        List<StaffDTO> staffDTOS = new ArrayList<>();

        for (Staff staff : staffs) {
            StaffDTO dto = StaffMapper.toDTO(staff);
            staffDTOS.add(dto);
        }
        return staffDTOS;
    }
    public StaffDTO entityToDtoObject(Staff staff){
        return StaffMapper.toDTO(staff);
    }

    public Staff dtoToEntity(StaffDTO staffDTO){
        return StaffMapper.toEntity(staffDTO,departmentDao,managerDao,titleDao);
    }

    @Override
    public Result getAll(){
        try {
            List<StaffDTO> staffDTOS = entityToDtoList(staffDao.findAll());
            LogLevelSave(2,"Tüm personeller listelendi");
            return new SuccessDataResult("Tüm personeller listelendi",true,staffDTOS);
        } catch (Exception e) {
            LogLevelSave(1,"Personel listeleme işlemi başarısız oldu");
            return new ErrorResult("Personel listeleme işlemi başarısız oldu",false);
        }
    }
    @Override
    public Result getById(Long id){
        try{
            StaffDTO staffDTO = entityToDtoObject(staffDao.getById(id));
            LogLevelSave(2,"İd değerine göre personel listelendi");
            return new SuccessDataResult("İd değerine ait personel listelendi",true,staffDTO);
        } catch (Exception e) {
            LogLevelSave(1,"Girilen id değerine ait bir personel bulunamadı");
            return new ErrorResult("Girilen id değerine ait bir personel bulunamadı",false);
        }
    }
    @Override
    public Result saveStaff(StaffDTO staffDTO){
        try{
            staffDao.save(dtoToEntity(staffDTO));
            LogLevelSave(3,"Personel kaydetme işlemi başarılır");
            return new SuccessDataResult("Personel ekleme işlemi başarılı",true,staffDTO);
        }catch (DataIntegrityViolationException e){
            LogLevelSave(1,"Bu mail veya telefon numarası zaten kayıtlı");
            return new ErrorResult("Bu mail veya telefon numarası zaten kayıtlı",false);
        } catch (Exception e) {
            LogLevelSave(1, "Personel ekleme işlemi başarısız");
            return new ErrorResult("Personel ekleme işlemi başarısız",false);
        }
    }

    @Override
    public Result updateStaff(Long id, StaffDTO staffDTO){

        try {
            Staff editStaff = staffDao.getById(id);
            editStaff.setName(staffDTO.getName());
            editStaff.setMail(staffDTO.getMail());
            editStaff.setPhoneNumber(staffDTO.getPhoneNumber());
            editStaff.setSalary(staffDTO.getSalary());
            editStaff.setSurName(staffDTO.getSurName());
            editStaff.setDepartment(departmentDao.getById(staffDTO.getDepartmentId()));
            editStaff.setManager(managerDao.getById(staffDTO.getManagerId()));
            editStaff.setTitle(titleDao.getById(staffDTO.getTitleId()));
            if (editStaff.getDepartment().getIsDeleted() || editStaff.getTitle().getIsDeleted() || editStaff.getManager().getIsDeleted()){
                LogLevelSave(1,"Personel güncelleme işleminde, ilişki olacağı tablo kaldırılmış.");
                return new ErrorResult("Personel güncelleme işleminde, ilişkili olacağı tablo kaldırılmış",false);
            }
            LogLevelSave(3,"Personel güncelleme işlemi başarılı.");
            return new SuccessDataResult("Personel güncelleme işlemi başarılı",true,entityToDtoObject(editStaff));
        } catch (DataIntegrityViolationException e) {
            LogLevelSave(1, "Email veya telefon numarası daha önceden alınmış");
            return new ErrorResult("Email veya telefon numarası daha önceden alınmış",false);
        }
        catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Bu id değerine ait bir personel bulunamadı.");
            return new ErrorResult("Bu id değerinde personel bulunamadı",false);
        }
    }
    @Override
    public Result deleteStaff(Long id){
        Staff deleteStaff;
        try {
            deleteStaff = staffDao.getById(id);
            LogLevelSave(3,"Yönetici silme İşlemi başarılı.");
            deleteStaff.setDeleted(true);
            staffDao.save(deleteStaff);
            return new SuccesResult("Yönetici silme işlemi başarılı",true);
        } catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            LogLevelSave(1, "Bu id değerine ait bir yönetici bulunamadı.");
            return new ErrorResult("Bu id değerinde yönetici bulunamadı",false);
        }
    }

}
