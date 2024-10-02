package com.dme.DormitoryProject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "logTbl")
public class Lgo extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "logDescriptionId")
    private LogLevel logLevel;
    private String message;

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
