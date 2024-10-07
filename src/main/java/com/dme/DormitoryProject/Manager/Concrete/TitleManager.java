package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.ITitleService;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.dtos.managerDtos.ManagerMapper;
import com.dme.DormitoryProject.dtos.titleDtos.TitleDTO;
import com.dme.DormitoryProject.dtos.titleDtos.TitleMapper;
import com.dme.DormitoryProject.entity.*;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import com.dme.DormitoryProject.repository.IStaffDao;
import com.dme.DormitoryProject.repository.ITitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TitleManager implements ITitleService {
    private ITitleDao titleDao;
    private IStaffDao staffDao;
    private ILgoDao lgoDao;
    private ILogLevelDao logLevelDao;


    @Autowired
    public TitleManager(ITitleDao titleDao, IStaffDao staffDao, ILgoDao lgoDao, ILogLevelDao logLevelDao) {
        this.titleDao = titleDao;
        this.staffDao = staffDao;
        this.lgoDao = lgoDao;
        this.logLevelDao = logLevelDao;
    }

    public void LogLevelSave(long id,String message){
        Lgo log = new Lgo();
        long searchLogLevelId= id;
        LogLevel logLevel = logLevelDao.findById(searchLogLevelId)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip LogLevel bulunamadı: " + searchLogLevelId));
        log.setLogLevel(logLevel);
        log.setAddDate(getMomentDate());
        log.setMessage(message);
        log.setDeleted(false);
        lgoDao.save(log);
    }
    public List<TitleDTO> entityToDto(List<Title> titles){
        List<TitleDTO> titleDTOS = new ArrayList<>();

        for (Title title : titles) {
            TitleDTO dto = TitleMapper.toDTO(title);
            titleDTOS.add(dto);
        }
        return titleDTOS;
    }

    public Title dtoToEntity(TitleDTO titleDTO){
        return TitleMapper.toEntity(titleDTO);
    }
    @Override
    public List<TitleDTO> getAll(){
        List<Title> titles = titleDao.findAll();
        LogLevelSave(2,"Tüm ünvanlar listelendi");
        return entityToDto(titles);
    }
    @Override
    public Optional<TitleDTO> getById(Long id) {
        List<TitleDTO> titleDTOS = entityToDto(titleDao.findAll());
        LogLevelSave(2, "Id değerine göre ünvan listelendi");
        return titleDTOS.stream()
                .filter(dto -> dto.getId().equals(id))
                .findFirst();
    }
    @Override
    public Title saveTitle(TitleDTO titleDTO){
        LogLevelSave(3,"Ünvan ekleme işlemi başarılı.");
        return titleDao.save(dtoToEntity(titleDTO));

    }
    @Override
    public Title updateTitle(Long id, TitleDTO titleDTO) {
        Title editTitle = titleDao.findById(id)
                .orElseThrow(() ->{
                    LogLevelSave(1,"Bu id değerine ait bir ünvan bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        editTitle.setName(titleDTO.getName());
        LogLevelSave(3,"ünvan güncelleme işlemi başarılı.");
        return titleDao.save(editTitle);
    }
    @Override
    public Title deleteTitle(Long id){
        Title deleteTitle = titleDao.findById(id)  //girilen id değerine ait departman olup olmadığının kontrolü
                .orElseThrow(()->{
                    LogLevelSave(1,"Bu id değerine ait bir ünvan bulunamadı.");
                    return new RuntimeException("Bu id'ye sahip veri yok: " + id);
                });
        List<Staff> staffList = staffDao.findAll();  // gireilen id değerini içeren bir staff olup olmadığının kontrolü
        for (Staff staff : staffList) {
            if (staff.getTitle().getId() == id) {
                LogLevelSave(4, "Bu ünvan personel ile ilişkili, siliniemez.");
                throw new RuntimeException("Bu ünvan personel ile ilişkili, silinemez.");
            }
        }
        LogLevelSave(3,"Departman silme İşlemi başarılı.");
        deleteTitle.setDeleted(true);
        return titleDao.save(deleteTitle);
    }
    public LocalDate getMomentDate(){
        return LocalDate.now();
    }
}
