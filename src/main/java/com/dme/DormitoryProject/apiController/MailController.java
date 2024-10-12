package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mail")
public class MailController {

    private IMailService mailService;

    @Autowired
    public MailController(IMailService mailService){
        this.mailService=mailService;
    }

    @GetMapping("/sendMail")
    public ResponseEntity<String> sendMail(){
        return ResponseEntity.ok(mailService.sendMail());
    }
}
