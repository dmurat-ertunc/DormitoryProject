package com.dme.DormitoryProject.dtos.departmentDtos;

public class DepartmentDTO {

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

    private String name;
    private Long id;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
