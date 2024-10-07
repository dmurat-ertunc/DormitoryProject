package com.dme.DormitoryProject.dtos.sportAreaDtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class SportAreaDTO {
    private Long id;
    @NotNull(message = "Spor türü alanı boş bırakılamaz")
    @NotEmpty(message = "Spor türü alanı boş bırakılamaz")
    private String sportType; // SportType enum'unun ismi

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }
}
