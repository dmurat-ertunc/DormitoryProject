package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.response.Result;

public interface IMailService {
    void sendMail(String mail,Long code);
    String sendMultiMediaMail();
}
