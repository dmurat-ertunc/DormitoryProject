package com.dme.DormitoryProject.dtos.rentalDtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class RentalDTO {
    private Long sportAreaId; // SportArea varlığının id'si
    private LocalDate rentalDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long studentId; // Student varlığının id'si
    private LocalDate studentBirthDate;
    private String studentMail;
    private String studentName;
    private String studentSurName;
    private String studentTcNo;
    private boolean studentVerify;


    public LocalDate getStudentBirthDate() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(LocalDate studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurName() {
        return studentSurName;
    }

    public void setStudentSurName(String studentSurName) {
        this.studentSurName = studentSurName;
    }

    public String getStudentTcNo() {
        return studentTcNo;
    }

    public void setStudenTcNo(String studentTcNo) {
        this.studentTcNo = studentTcNo;
    }

    public boolean isStudentVerify() {
        return studentVerify;
    }

    public void setStudentVerify(boolean studentVerify) {
        this.studentVerify = studentVerify;
    }

    public Long getSportAreaId() {
        return sportAreaId;
    }

    public void setSportAreaId(Long sportAreaId) {
        this.sportAreaId = sportAreaId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
