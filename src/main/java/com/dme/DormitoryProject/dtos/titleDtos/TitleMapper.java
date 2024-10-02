package com.dme.DormitoryProject.dtos.titleDtos;

import com.dme.DormitoryProject.entity.Title;

public class TitleMapper {
    public static TitleDTO toDTO(Title title){
        TitleDTO titleDTO = new TitleDTO();
        titleDTO.setId(title.getId());
        titleDTO.setName(title.getName());

        return titleDTO;
    }
    public static Title toEntity(TitleDTO titleDTO){
        Title title = new Title();

        title.setName(titleDTO.getName());

        return title;
    }
}
