package com.SmartContactManager.Smart.Contact.Manager.Final.Configuration;

import com.SmartContactManager.Smart.Contact.Manager.Final.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CustomaAuthSuccessHandler customaAuthSuccessHandler;

    @Autowired
    AuthFailHandler authFailHandler;

    @Autowired
    private CustomAuthorizationRequestResolver customResolver;

    @Autowired
    oAuthAuthenticationsucessHandler oAuthAuthenticationsucessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){


        httpSecurity.csrf(csrf -> csrf.disable());

        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/register", "/user/userRegister","/user/login","/user/authenticate","/user/OtpSend","/user/transfer","/user/userotpConfirmation").permitAll() // Allow register
                .requestMatchers("/","/index.html","/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/user/**").authenticated()
                .anyRequest().authenticated()// others need login;
        );

        httpSecurity.authenticationProvider(daoAuthenticationProvider());




        httpSecurity.formLogin(form->{
           form.loginPage("/user/login");
           form.loginProcessingUrl("/user/authenticate");
           form.defaultSuccessUrl("/user/dashboard");
           form.usernameParameter("email");
           form.passwordParameter("password");
           form.failureHandler(authFailHandler);
        });

        httpSecurity.logout(logoutform->{
           logoutform.logoutUrl("/logout");
           logoutform.clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
           logoutform.logoutSuccessUrl("/user/login?logout=true");
        });


       httpSecurity.oauth2Login(oauth2->{
           oauth2.loginPage("/user/login");
           oauth2.authorizationEndpoint(auth -> auth
                   .authorizationRequestResolver(customResolver))
           .successHandler(customaAuthSuccessHandler);

       });


        return httpSecurity.build();

    }

//    @Bean
//    public HttpFirewall allowMoreParametersFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        // Allow up to 500 parameters (default is often 100)
//        firewall.setMaxRequestUriLength(2000); // adjust as needed
//        firewall.setMaxRequestUrlLength(2000); // adjust as needed
//        firewall.setMaxParameterCount(500); // adjust this specific count
//        return firewall;
//    }
}
