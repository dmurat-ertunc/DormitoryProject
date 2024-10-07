package com.dme.DormitoryProject.dtos.departmentDtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DepartmentDTO {

    private Long id;
    @NotNull(message = "Departman ismi alanı boş geçilemez")
    @NotEmpty(message = "Departman isi alanı boş geçilemez")
    private String name;

    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
