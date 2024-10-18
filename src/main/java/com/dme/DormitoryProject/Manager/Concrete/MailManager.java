package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IMailService;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccesResult;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailManager implements IMailService {
    private JavaMailSender mailSender;

    @Autowired
    public MailManager(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(String mail, Long code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("cengdme@gmail.com");
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setText(String.valueOf(code));
        simpleMailMessage.setSubject("Doğrulama Kodu");
        mailSender.send(simpleMailMessage);
    }

    @Override
    public String sendMultiMediaMail() {
        return "";
    }
}
