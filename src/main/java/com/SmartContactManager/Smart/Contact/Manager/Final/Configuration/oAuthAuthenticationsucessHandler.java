package com.SmartContactManager.Smart.Contact.Manager.Final.Configuration;

import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.Provider;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class oAuthAuthenticationsucessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User user =(DefaultOAuth2User)authentication.getPrincipal();

        String email=user.getAttribute("email").toString();
        String name= user.getAttribute("name").toString();
        String picture=user.getAttribute("picture").toString();

        User new_user=new User();
        new_user.setEmail(email);
        new_user.setName(name);
        new_user.setEmail(email);
        new_user.setProfilepic(picture);
        new_user.setEnabled(true);
        new_user.setProvider(Provider.GOOGLE);
        new_user.setPassword("password");
        new_user.setUserid(UUID.randomUUID().toString());
        new_user.setEmailVerified(true);
        new_user.setRolelist(List.of("ROLE_USER"));
        new_user.setProviderid(user.getName());
        new_user.setAbout("this account is created using google");

        User user2=userRepository.findByEmail(email).orElse(null);

        if(user==null){
            userRepository.save(new_user);
        }

         new DefaultRedirectStrategy().sendRedirect(request,response,"/user/dashboard");
    }
}
