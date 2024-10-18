package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IDepartmentService;
import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.IDepartmentDao;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IStaffDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentManager implements IDepartmentService{

    private static final Logger log = LoggerFactory.getLogger(DepartmentManager.class);
    private IDepartmentDao departmentDao;
    private IStaffDao staffDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;

    @Autowired
    public DepartmentManager(IDepartmentDao departmentDao, IStaffDao staffDao,ILgoDao lgoDao,ILogLevelDao logLevelDao){
        this.departmentDao=departmentDao;
        this.staffDao=staffDao;
        this.lgoDao=lgoDao;
        this.logLevelDao=logLevelDao;
    }

    public void logLevelSave(long id,String message){
        Lgo log = new Lgo();
        LogLevel logLevel = logLevelDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + id));
        log.setLogLevel(logLevel);
        log.setMessage(message);
        lgoDao.save(log);
    }

    public List<DepartmentDTO> entityToDtoList(List<Department> departments){
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for (Department department : departments) {
            DepartmentDTO dto = DepartmentMapper.toDTO(department);
            departmentDTOS.add(dto);
        }
        return departmentDTOS;
    }

    public DepartmentDTO entityToDtoObject(Department department){
        return DepartmentMapper.toDTO(department);
    }

    public Department dtoToEntity(DepartmentDTO departmentDTO){
        return DepartmentMapper.toEntity(departmentDTO);
    }

    @Override
    public Result getAll(){
        try {
            List<DepartmentDTO> departmentDTOS = entityToDtoList(departmentDao.findAll());
            logLevelSave(2,"Tüm departmanlar listelendi");
            return new SuccessDataResult("Tüm departmanlar listelendi",true,departmentDTOS);
        } catch (Exception e) {
            logLevelSave(1,"Departman listeleme işleminde hata oluştu");
            return new ErrorResult("Departman listeleme işleminde hata oluştu",false);
        }
    }
    @Override
    public Result getById(Long id){
        try {
            Department findDepartment = departmentDao.getById(id);
            logLevelSave(3,"İd değerine göre departman listeleme işlemi başarılı");
            return new SuccessDataResult("İd değerine göre departman verisi, başarılı bir şekilde döndürüldü",true, entityToDtoObject(findDepartment));
        } catch (Exception e) {
            // Eğer varlık bulunamadıysa, bu blok çalışır
            logLevelSave(1, "Bu id değerine ait bir departman bulunamadı.");
            return new ErrorResult("Bu id değerine ait departman bulunamadı",false);
        }
    }
    @Override
    public Result saveDepartment(DepartmentDTO departmentDTO){
        try {
            departmentDao.save(dtoToEntity(departmentDTO));
            logLevelSave(3,"Departman ekleme işlemi başarılı");
            return new SuccessDataResult("Departman ekleme işlemi başarılı",true,departmentDTO);
        }catch (Exception e) {
            logLevelSave(1,"Departman ekleme  işlemi başarısız");
            return new ErrorResult("Departman ekleme işlemi başarısız",false);
        }
    }
    @Override
    public Result updateDepartment(Long id,DepartmentDTO departmentDTO){
        Department editDepartment;
        try{
            editDepartment = departmentDao.getById(id);
            editDepartment.setName(departmentDTO.getName());
            departmentDao.save(editDepartment);
            logLevelSave(3,"Departman güncelleme işlemi başarılı");
            return new SuccessDataResult("Departman güncelleme işlemi başarılı",true,entityToDtoObject(editDepartment));
        }catch (Exception e){
            logLevelSave(1,"Bu id değerine ait departman bulunamadı");
            return new ErrorResult("Bu id değerine ait departman bulunamadı",false);
        }
    }
    @Override
    public Result deleteDepartment(Long id)
    {
        Department deleteDepartment;
        try{
            deleteDepartment = departmentDao.getById(id);
            List<Staff> staffList = staffDao.findAll();
            for (Staff staff : staffList){
                if(staff.getDepartment().getId() == id){
                    logLevelSave(4,"Bu departman, personel ile ilişki silinemedi");
                    return new ErrorResult("Bu departman, personel ile ilişkili olduğu için silinemedi",false);
                }
            }
            deleteDepartment.setDeleted(true);
            departmentDao.save(deleteDepartment);
            logLevelSave(3, "Departman başarılı şekilde silindi");
            return new SuccesResult("Departman başarılı şekilde silindi",true);
        } catch (Exception e) {
            logLevelSave(1,"Bu id değerine ait departman bulunamadı");
            return new ErrorResult("Bu id değerine ait departman bulunamadı",false);
        }
    }
    @Override
    public Result startingWithWord(String prefix){
        List<Department> departments = departmentDao.findByNameStartingWith(prefix);
        if (departments !=  null && !departments.isEmpty()){
            logLevelSave(3, "Baş harfi değerine göre departman veya departmanlar başarılı şekilde listelendi");
            return new SuccessDataResult("Baş harfi değerine göre departman veya departmanlar başarılı şekilde listelendi",true,entityToDtoList(departments));
        }
        logLevelSave(1,"Bu harf ile başlayan departman departman bulunamadı");
        return new ErrorResult("Bu harf ile başlayan departman departman bulunamadı",false);
    }


}
