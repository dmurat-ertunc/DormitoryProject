package com.dme.DormitoryProject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "logLevelTbl")
public class LogLevel extends BaseEntity{

    private String description;
    @OneToMany(mappedBy = "logLevel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lgo> lgoList;

    public List<Lgo> getLgoList() {
        return lgoList;
    }
    public void setLgoList(List<Lgo> lgoList) {
        this.lgoList = lgoList;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
