package com.dme.DormitoryProject.dtos.rentalDtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class RentalDTO {
    private Long id;
    @NotEmpty(message = "Kiralamanın bir spor alanına bağlı olması gerekir")
    @NotNull(message = "Kiralamanın bir spor alanına bağlı olması gerekir")
    private Long sportAreaId;
    private String sporType;// SportArea varlığının id'si
    @NotEmpty(message = "Kiralama tarihi alanı boş bırakılamaz")
    @NotNull(message = "Kiralama tarihi alanı boş bırakılamaz")
    private LocalDate rentalDate;
    @NotEmpty(message = "Kiralama başlangıç saati alanı boş bırakılamaz")
    @NotNull(message = "Kiralama başlangıç saati alanı boş bırakılamaz")
    private LocalTime startTime;
    @NotEmpty(message = "Kiralama bitiş saati alanı boş bırakılamaz")
    @NotNull(message = "Kiralama bitiş saati alanı boş bırakılamaz")
    private LocalTime endTime;
    @NotEmpty(message = "Kiralamanın bir öğrenci tarafından yapılması gerekmektedir")
    @NotNull(message = "Kiralamanın bir öğrenci tarafından yapılması gerekmektedir")
    private Long studentId; // Student varlığının id'si
    private LocalDate studentBirthDate;
    private String studentMail;
    private String studentName;
    private String studentSurName;
    private String studentTcNo;
    private boolean studentVerify;
    public RentalDTO(Long id, Long sportAreaId, String sporType, LocalDate rentalDate, LocalTime startTime, LocalTime endTime, Long studentId, LocalDate studentBirthDate, String studentMail, String studentName, String studentSurName, String studentTcNo, boolean studentVerify) {
        this.id = id;
        this.sportAreaId = sportAreaId;
        this.sporType = sporType;
        this.rentalDate = rentalDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.studentId = studentId;
        this.studentBirthDate = studentBirthDate;
        this.studentMail = studentMail;
        this.studentName = studentName;
        this.studentSurName = studentSurName;
        this.studentTcNo = studentTcNo;
        this.studentVerify = studentVerify;
    }
    public RentalDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSporType() {
        return sporType;
    }

    public void setSporType(String sporType) {
        this.sporType = sporType;
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
