package com.dme.DormitoryProject.dtos.logLevelDtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LogLevelDTO {
    private Long id;
    @NotEmpty(message = "Log açıklaması alanı boş geçilemez")
    @NotNull(message = "Log açıklaması alanı boş geçilemez")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
