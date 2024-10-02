package com.dme.DormitoryProject.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departmentTbl")
public class Department extends BaseEntity{

    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Staff> staffList;

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public List<Staff> getStaffList(){
        return staffList;
    }
    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
