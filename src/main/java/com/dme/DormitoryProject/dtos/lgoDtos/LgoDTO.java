package com.dme.DormitoryProject.dtos.lgoDtos;

import java.time.LocalDate;

public class LgoDTO {

    private Long id;
    private Long logLeveId;
    private String message;
    private String logLevelDescription;
    private LocalDate date;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogLevelDescription() {
        return logLevelDescription;
    }

    public void setLogLevelDescription(String logLevelDescription) {
        this.logLevelDescription = logLevelDescription;
    }

    public Long getLogLeveId() {
        return logLeveId;
    }

    public void setLogLeveId(Long logLeveId) {
        this.logLeveId = logLeveId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
