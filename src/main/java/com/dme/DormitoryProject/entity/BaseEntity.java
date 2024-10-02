package com.dme.DormitoryProject.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {

    LocalDate date = LocalDate.now();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    private boolean isDeleted = false;
    private LocalDate addDate = date;

    public long getId(){
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public boolean getIsDeleted(){
        return isDeleted;
    }

    public LocalDate getAddDate() {
        return addDate;
    }
    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }
}
