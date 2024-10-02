package com.dme.DormitoryProject.dtos.sportAreaDtos;

import java.util.List;

public class SportAreaDTO {
    private Long id;
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
