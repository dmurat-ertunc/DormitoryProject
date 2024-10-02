package com.dme.DormitoryProject.dtos.lgoDtos;

public class LgoDTO {

    private Long logLeveId;
    private String message;
    private String logLevelDescription;


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
}
