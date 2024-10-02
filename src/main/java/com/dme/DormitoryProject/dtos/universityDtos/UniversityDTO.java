package com.dme.DormitoryProject.dtos.universityDtos;

import java.util.HashSet;
import java.util.Set;

public class UniversityDTO {
    private Long id;
    private String name;
    private String mail;
    private String phoneNumber;
    private String city;
    private String studentName;
    private Set<Long> studentIds = new HashSet<>(); // Öğrenci ID'leri

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Set<Long> getStudentIds() {
        return studentIds;
    }
    public void setStudentIds(Set<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
