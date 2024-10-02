package com.dme.DormitoryProject.dtos.managerDtos;

public class ManagerDTO {
    private String mail;
    private String name;
    private String surName;
    private String phoneNumber;
    private int salary;
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
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
