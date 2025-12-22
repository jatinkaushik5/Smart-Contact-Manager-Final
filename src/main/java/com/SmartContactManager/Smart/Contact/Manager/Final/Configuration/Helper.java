package com.SmartContactManager.Smart.Contact.Manager.Final.Configuration;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.security.Principal;

public class Helper {

    public static String getEmailofLoggedUser(Authentication authentication){
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication1.getPrincipal();
//       agar login login with google se hai
        if(principal instanceof OAuth2AuthenticatedPrincipal oAuth2AuthenticatedPrincipal){
            var oAuth2AuthenticationToken=(OAuth2AuthenticationToken)authentication;

//            to known kha se login google,github
           var clientid= oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
           String email=oAuth2AuthenticatedPrincipal.getAttribute("email");
           System.out.println("Login with Google");
           return email ;
        }
        else{
            return authentication.getName();
        }
    }
}
