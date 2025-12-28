package com.SmartContactManager.Smart.Contact.Manager.Final.Service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    private final Dotenv dotenv;

    @Autowired
    public MyService(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public void printEnv() {
        String dbUser = dotenv.get("MYSQL_USER");
        System.out.println("DB User: " + dbUser);
    }
}
