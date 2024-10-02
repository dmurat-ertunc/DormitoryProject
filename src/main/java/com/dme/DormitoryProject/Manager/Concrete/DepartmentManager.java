package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IDepartmentService;
import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.IDepartmentDao;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IStaffDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentManager implements IDepartmentService{

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

    public List<DepartmentDTO> entityToDto(List<Department> departments){
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for (Department department : departments) {
            DepartmentDTO dto = DepartmentMapper.toDTO(department);
            departmentDTOS.add(dto);
        }
        return departmentDTOS;
    }

    public Department dtoToEntity(DepartmentDTO departmentDTO){
        return DepartmentMapper.toEntity(departmentDTO);
    }

    @Override
    public List<DepartmentDTO> getAll(){
        List<Department> departmentList = departmentDao.findAll();
        logLevelSave(2, "Tüm departmanlar listelendi");
        return entityToDto(departmentList);
    }
    @Override
    public Optional<DepartmentDTO> getById(Long id){
        List<DepartmentDTO> departmentDTOS = entityToDto(departmentDao.findAll());
        logLevelSave(2, "Id değerine göre departman listelendi");
        return departmentDTOS.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst();
    }
    @Override
    public Department saveDepartment(DepartmentDTO departmentDTO){
        if (Objects.equals(departmentDTO.getName(), "")){
            logLevelSave(4,"Departman ekleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Departman ekleme işleminde boş alan bırakılamaz. " + 4);
        }
        logLevelSave(3,"Departman ekleme işlemi başarılı.");
        return departmentDao.save(dtoToEntity(departmentDTO));
    }
    @Override
    public Department updateDepartment(Long id,DepartmentDTO departmentDTO){
        Department editDepartment = departmentDao.findById(id)
                .orElseThrow(()-> {
                    logLevelSave(1,"Bu id değerine ait bir yönetici bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });

        if (Objects.equals(departmentDTO.getName(), "")){
            logLevelSave(4,"Departman güncelleme işleminde boş alan bırakılamaz.");
            throw new RuntimeException("Departman güncelleme işleminde boş alan bırakılamaz. " + id);
        }
        editDepartment.setName(departmentDTO.getName());
        logLevelSave(3,"Departman güncelleme işlemi başarılı.");
        return departmentDao.save(editDepartment);
    }

    public Department deleteDepartment(Long id)
    {
        Department deleteDepartment = departmentDao.findById(id)  //girilen id değerine ait departman olup olmadığının kontrolü
                .orElseThrow(()->{
                    logLevelSave(1,"Bu id değerine ait bir departman bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        List<Staff> staffList = staffDao.findAll();  // gireilen id değerini içeren bir staff olup olmadığının kontrolü
        for (Staff staff : staffList) {
            if (staff.getDepartment().getId() == id) {
                logLevelSave(4, "Bu departman personel ile ilişkili, siliniemez.");
                throw new RuntimeException("Bu departman personel ile ilişkili, silinemez.");
            }
        }
        logLevelSave(3,"Departman silme İşlemi başarılı.");
        deleteDepartment.setDeleted(true);
        return departmentDao.save(deleteDepartment);
    }


}
