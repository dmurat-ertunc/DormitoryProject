package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.titleDtos.TitleDTO;
import com.dme.DormitoryProject.entity.Title;


import java.util.List;
import java.util.Optional;

public interface ITitleService {
    List<TitleDTO> getAll();
    Optional<TitleDTO> getById(Long id);
    Title saveTitle(TitleDTO titleDTO);
    Title updateTitle(Long id, TitleDTO titleDTO);
    Title deleteTitle(Long id);
}
