package com.SmartContactManager.Smart.Contact.Manager.Final;

import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.Provider;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import com.SmartContactManager.Smart.Contact.Manager.Final.Service.EmailService;
import com.SmartContactManager.Smart.Contact.Manager.Final.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class ApplicationTests {

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    EmailService service;

    @Autowired
    UserRepository userService;

	@Test
	void contextLoads() {
	}




}
