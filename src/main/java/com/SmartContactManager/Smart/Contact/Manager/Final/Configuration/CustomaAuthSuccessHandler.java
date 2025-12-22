package com.SmartContactManager.Smart.Contact.Manager.Final.Configuration;

import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.Provider;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomaAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User defaultOAuth2User=(DefaultOAuth2User) authentication.getPrincipal();

        String email=defaultOAuth2User.getAttribute("email");
        User user=userRepository.findByEmail(email).orElse(null);

        if(user==null){
            User user1=new User();
            user1.setEmail(email);
            user1.setEmailVerified(true);
            user1.setAbout("Login in with Google");
            user1.setProvider(Provider.GOOGLE);
            userRepository.save(user1);
        }

        response.sendRedirect("/user/dashboard");

    }
}
