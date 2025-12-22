package com.SmartContactManager.Smart.Contact.Manager.Final.Controller;

import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ApiController {

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
}
