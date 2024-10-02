package com.dme.DormitoryProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "studentTbl")
public class Student extends BaseEntity{

    private String name;
    private String surName;
    private String tcNo;
    private String mail;
    private LocalDate birthDate;

    @ManyToMany
    @JoinTable(
            name = "student_university", // ilişkiyi temsil eden ara tablo
            joinColumns = @JoinColumn(name = "student_id"), // öğrenci ile ilgili sütun
            inverseJoinColumns = @JoinColumn(name = "univercity_id") // üniversiteyi ile ilgili sütun
    )
    //@JsonManagedReference
    private Set<University> university = new HashSet<>();
    private boolean verify;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentalList;

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
    public String getTcNo(){
        return tcNo;
    }
    public void setTcNo(String tcNo){
        this.tcNo=tcNo;
    }
    public String getMail(){
        return mail;
    }
    public void setMail(String mail){
        this.mail=mail;
    }
    public Set<University> getUniversity() {
        return university;
    }
    public void setUniversity(Set<University> university) {
        this.university = university;
    }
    public boolean getVerify() {
        return verify;
    }
    public void setVerify(boolean verify) {
        this.verify = verify;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isVerify() {
        return verify;
    }

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }
}



//@OneToOne(cascade = CascadeType.ALL)   //Bir kullanıcı kaydedildiğinde veya silindiğinde, ilişkili adres entity'si de aynı işlemi geçirecektir.
//@JoinColumn(name = "universityId", referencedColumnName = "id") //Studebt tablosunda, University tablosundaki id sütununu referans alan universityId isimli bir yabancı anahtar oluşturur.
