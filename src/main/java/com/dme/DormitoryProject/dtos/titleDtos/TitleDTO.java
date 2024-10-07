package com.dme.DormitoryProject.dtos.titleDtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TitleDTO {
    @NotNull(message = "Ünvan ismi alanı boş bırakılamaz")
    @NotEmpty(message = "Ünvan ismi alanı boş bırakılamaz")
    private String name;
    private Long id;

    public TitleDTO(){

    }

    public TitleDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
