package com.dme.DormitoryProject.dtos.staffDtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class StaffDTO {

    private Long id;
    @NotNull(message = "Çalışan isim alanı boş bırakılamaz")
    @NotEmpty(message = "Çalışan isim alanı boş bırakılamaz")
    private String name;
    @NotNull(message = "Çalışan soyisim alanı boş bırakılamaz")
    @NotEmpty(message = "Çalışan soyisim alanı boş bırakılamaz")
    private String surName;
    @NotNull(message = "Çalışan mail alanı boş bırakılamaz")
    @NotEmpty(message = "Çalışan mail alanı boş bırakılamaz")
    @Column(unique = true)
    private String mail;
    @NotNull(message = "Çalışan maaş alanı boş bırakılamaz")
    private int salary;
    @NotNull(message = "Çalışan telefon numarası alanı boş bırakılamaz")
    @NotEmpty(message = "Çalışan telefon numarası alanı boş bırakılamaz")
    @Column(unique = true)
    private String phoneNumber;
    @NotNull(message = "Çalışanın bağlı olduğu departman Id alanı boş bırakılamaz")
    private Long departmentId; // Department'ın ID'si
    private String departmentName;
    @NotNull(message = "Çalışan bağlı olduğu ünvan Id alanı boş bırakılamaz")
    private Long titleId; // Title'ın ID'si
    private String titleName;
    @NotNull(message = "Çalışan bağlı olduğu yönetici Id alanı boş bırakılamaz")
    private Long managerId; // Manager'ın ID'si
    private String managerMail;
    private String managerName;
    private String managerPhoneNumber;
    private int managerSalary;
    private String managerSurName;
    private String managerTitle;

    public StaffDTO(Long id, String name, String surName, String mail, int salary, String phoneNumber, Long departmentId, String departmentName, Long titleId, String titleName, Long managerId, String managerMail, String managerName, String managerPhoneNumber, int managerSalary, String managerSurName, String managerTitle) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.mail = mail;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.titleId = titleId;
        this.titleName = titleName;
        this.managerId = managerId;
        this.managerMail = managerMail;
        this.managerName = managerName;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerSalary = managerSalary;
        this.managerSurName = managerSurName;
        this.managerTitle = managerTitle;
    }

    public StaffDTO(){

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getManagerTitle() {
        return managerTitle;
    }
    public void setManagerTitle(String managerTitle) {
        this.managerTitle = managerTitle;
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
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName(){
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Long getTitleId() {
        return titleId;
    }
    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
    public String getTitleName(){
        return titleName;
    }
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    public Long getManagerId() {
        return managerId;
    }
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
    public String getManagerMail() {
        return managerMail;
    }
    public void setManagerMail(String managerMail) {
        this.managerMail = managerMail;
    }
    public String getManagerName() {
        return managerName;
    }
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }
    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }
    public int getManagerSalary() {
        return managerSalary;
    }
    public void setManagerSalary(int managerSalary) {
        this.managerSalary = managerSalary;
    }
    public String getManagerSurName() {
        return managerSurName;
    }
    public void setManagerSurName(String managerSurName) {
        this.managerSurName = managerSurName;
    }

}
