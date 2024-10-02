package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.ILgoService;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.repository.ILogLevelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LgoManager implements ILgoService {
    private ILgoDao lgoDao;

    @Autowired
    public LgoManager(ILgoDao lgoDao){
        this.lgoDao=lgoDao;
    }
    @Override
    public List<Lgo> getAll(){
        return lgoDao.findAll();
    }
    @Override
    public Lgo getById(Long id){
        return  lgoDao.findLgoById(id);
    }
    @Override
    public Lgo saveLgo(Lgo lgo){
        LocalDate date = LocalDate.now();
        lgo.setAddDate(date);
        return lgoDao.save(lgo);
    }
}
