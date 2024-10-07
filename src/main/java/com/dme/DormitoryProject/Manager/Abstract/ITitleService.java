package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.titleDtos.TitleDTO;
import com.dme.DormitoryProject.entity.Title;
import com.dme.DormitoryProject.response.Result;


import java.util.List;
import java.util.Optional;

public interface ITitleService {
    Result getAll();
    Result getById(Long id);
    Result saveTitle(TitleDTO titleDTO);
    Result updateTitle(Long id, TitleDTO titleDTO);
    Result deleteTitle(Long id);
}
