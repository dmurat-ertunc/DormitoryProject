package com.dme.DormitoryProject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "rentalTbl")
public class Rental extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "sportAreaId", nullable = false)
    private SportArea sportArea;

    private LocalDate rentalDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    public SportArea getSportArea() {
        return sportArea;
    }

    public void setSportArea(SportArea sportArea) {
        this.sportArea = sportArea;
    }
    public LocalDate getRentalDate() {
        return rentalDate;
    }
    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}