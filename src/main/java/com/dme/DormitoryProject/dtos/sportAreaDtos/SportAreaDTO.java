package com.dme.DormitoryProject.dtos.sportAreaDtos;

import java.util.List;

public class SportAreaDTO {
    private String sportType; // SportType enum'unun ismi
    private List<Long> rentalIds; // Rental varlıklarının id'leri


    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public List<Long> getRentalIds() {
        return rentalIds;
    }

    public void setRentalIds(List<Long> rentalIds) {
        this.rentalIds = rentalIds;
    }
}
