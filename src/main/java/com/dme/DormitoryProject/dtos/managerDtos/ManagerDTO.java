package com.dme.DormitoryProject.dtos.managerDtos;


import com.dme.DormitoryProject.annotations.MailUniqueCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ManagerDTO {
    //@MailUniqueCheck
    @NotEmpty(message = "Mail alanı boş bırakılamaz")
    @NotNull(message = "Mail alanı boş bırakılmaz")
    @Email(message = "Mail alanını uygun formatta giriniz")
    @Column(unique = true)
    private String mail;
    @NotEmpty(message = "İsim alanı boş bırakılamaz")
    @NotNull(message = "İsim alanı boş bırakılmaz")
    private String name;
    @NotEmpty(message = "Soyisim alanı boş bırakılamaz")
    @NotNull(message = "Soyisim alanı boş bırakılmaz")
    private String surName;
    @Pattern(regexp = "^[0-9]+$", message = "Sadece rakam içermelidir.")
    @Pattern(regexp = "^0\\d{10}$", message = "Telefon numarası 0 ile başlamalı ve 11 karakter olmalıdır.")
    @NotEmpty(message = "Telefon numarası alanı boş bırakılamaz")
    @NotNull(message = "Telefon numarası alanı boş bırakılmaz")
    @Column(unique = true)
    private String phoneNumber;
    @NotNull(message = "Maaş alanı boş bırakılmaz")
    private int salary;
    @NotEmpty(message = "Ünvan alanı boş bırakılamaz")
    @NotNull(message = "Ünvan alanı boş bırakılmaz")
    private String title;

    private Long id;

    public ManagerDTO(){

    }
    public ManagerDTO(String mail, String name, String surName, String phoneNumber, int salary, String title, Long id) {
        this.mail = mail;
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.title = title;
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getMail() {
        return mail;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
