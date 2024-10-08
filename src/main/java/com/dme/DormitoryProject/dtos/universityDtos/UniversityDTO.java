package com.dme.DormitoryProject.dtos.universityDtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

public class UniversityDTO {
    private Long id;
    @NotNull(message = "Üniversite isim alanı boş geçilemez")
    @NotEmpty(message = "Üniversite isim alanı boş geçilemez")
    private String name;
    @NotNull(message = "Üniversite mail alanı boş geçilemez")
    @NotEmpty(message = "Üniversite mail alanı boş geçilemez")
    @Column(unique = true)
    @Email
    private String mail;
    @Pattern(regexp = "^[0-9]+$", message = "Sadece rakam içermelidir.")
    @Pattern(regexp = "^0\\d{10}$", message = "Telefon numarası 0 ile başlamalı ve 11 karakter olmalıdır.")
    @NotNull(message = "Üniversite telefon numarası alanı boş geçilemez")
    @NotEmpty(message = "Üniversite telefon numarası alanı boş geçilemez")
    @Column(unique = true)
    private String phoneNumber;
    @NotNull(message = "Üniversite şehir alanı boş geçilemez")
    @NotEmpty(message = "Üniversite şehir alanı boş geçilemez")
    private String city;
    private Set<String> studentName;
    private Set<Long> studentIds = new HashSet<>(); // Öğrenci ID'leri
    public UniversityDTO(){

    }

    public UniversityDTO(Long id, String name, String mail, String phoneNumber, String city, Set<String> studentName, Set<Long> studentIds) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.studentName = studentName;
        this.studentIds = studentIds;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Set<String> getStudentName() {
        return studentName;
    }
    public void setStudentName(Set<String> studentName) {
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
