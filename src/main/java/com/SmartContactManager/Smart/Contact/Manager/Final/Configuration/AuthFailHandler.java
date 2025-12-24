package com.SmartContactManager.Smart.Contact.Manager.Final.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.ui.Model;

import java.io.IOException;

@Configuration
public class AuthFailHandler  implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if(exception instanceof DisabledException){

//            user is disable
            request.getSession().setAttribute("formAction","/user/OtpSend");
            request.getSession().setAttribute("notvalid","Please Verify Your Account!");
            response.sendRedirect("/user/login?notEnabled=true");
        }
        else{
            response.sendRedirect("/user/login");
        }
    }
}
