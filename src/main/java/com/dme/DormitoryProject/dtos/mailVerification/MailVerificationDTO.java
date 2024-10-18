package com.dme.DormitoryProject.dtos.mailVerification;

public class MailVerificationDTO {
    private Long code;

    public MailVerificationDTO() {
    }

    public MailVerificationDTO(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
