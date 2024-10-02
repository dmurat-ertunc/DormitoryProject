package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.entity.Lgo;

import java.util.List;


public interface ILgoService {
    List<Lgo> getAll();
    Lgo getById(Long id);
    Lgo saveLgo(Lgo lgo);
}
