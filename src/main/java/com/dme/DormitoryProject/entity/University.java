package com.dme.DormitoryProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "universityTbl")
public class University extends BaseEntity{

    private String name;
    private String mail;
    private String phoneNumber;
    private String city;
    // Bir üniversitenin birden çok öğrencisi olabilir
    @ManyToMany(mappedBy = "university") // iki taraflı ilişkiyi tanımlamak için
    //@JsonBackReference
    private Set<Student> students = new HashSet<>();

    //mappedBy = "university": Öğrenci entity'sindeki university alanı üzerinden ilişkiyi yönetir.
    //cascade = CascadeType.ALL: Üniversite üzerinde yapılan işlemler (kaydetme, silme) öğrencilere de uygulanır.
    //orphanRemoval = true: Eğer öğrenci listeden çıkarılırsa, ilişkili öğrenci de silinir.

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getmail(){
        return mail;
    }
    public void setmail(String mail){
        this.mail=mail;
    }
    public String getphoneNumber(){
        return phoneNumber;
    }
    public void setphoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public String getcity(){
        return city;
    }
    public void setcity(String city){
        this.city=city;
    }
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }


}
