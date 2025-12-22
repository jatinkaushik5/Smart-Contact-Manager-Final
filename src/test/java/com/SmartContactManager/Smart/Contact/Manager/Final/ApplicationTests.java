package com.SmartContactManager.Smart.Contact.Manager.Final;

import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

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
