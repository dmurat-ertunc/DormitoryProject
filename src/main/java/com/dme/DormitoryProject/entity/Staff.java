package com.dme.DormitoryProject.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "staffTbl")
public class Staff extends BaseEntity{

    private String name;
    private String surName;
    private String mail;
    private int salary;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "titleId")
    private Title title;
    @ManyToOne
    @JoinColumn(name = "joinToManagerId")
    private Manager manager;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getSurName(){
        return surName;
    }
    public void setSurName(String surName){
        this.surName=surName;
    }
    public String getMail(){
        return mail;
    }
    public void setMail(String mail){
        this.mail=mail;
    }
    public int getSalary(){
        return  salary;
    }
    public void setSalary(int salary){
        this.salary=salary;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public Department getDepartment(){
        return department;
    }
    public void setDepartment(Department department){
        this.department=department;
    }
    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
