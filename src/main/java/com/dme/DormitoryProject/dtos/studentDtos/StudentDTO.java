package com.dme.DormitoryProject.dtos.studentDtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StudentDTO {
    private String name;
    private String surName;
    private String tcNo;
    private String mail;
    private LocalDate birthDate;
    private Set<Long> universityIds = new HashSet<>(); // Üniversite ID'leri
    private boolean verify;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public String getTcNo() {
        return tcNo;
    }
    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public Set<Long> getUniversityIds() {
        return universityIds;
    }
    public void setUniversityIds(Set<Long> universityIds) {
        this.universityIds = universityIds;
    }
    public boolean isVerify() {
        return verify;
    }
    public void setVerify(boolean verify) {
        this.verify = verify;
    }
}
