package com.SmartContactManager.Smart.Contact.Manager.Final.Controller;

import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/emailsend")
    public String SEND(){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("kaushikjatin532004@gmail.com");
        message.setTo("2222.btech@igu.ac.in");
        message.setSubject("testing emails");
        message.setText("Hello success");
        javaMailSender.send(message);
        return "sucess>";
    }
}
