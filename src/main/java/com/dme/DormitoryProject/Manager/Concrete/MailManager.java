package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IMailService;
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
    public String sendMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("cengdme@gmail.com");
        simpleMailMessage.setTo("enesaktepe181@gmail.com");
        simpleMailMessage.setText("dme dme dme dme");
        simpleMailMessage.setSubject("Sakın açma lan");
        mailSender.send(simpleMailMessage);
        return "Gönderildi";
    }

    @Override
    public String sendMultiMediaMail() {
        return "";
    }
}
