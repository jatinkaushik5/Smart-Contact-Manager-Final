package com.SmartContactManager.Smart.Contact.Manager.Final.Service;

import com.SmartContactManager.Smart.Contact.Manager.Final.Configuration.Helper;
import com.SmartContactManager.Smart.Contact.Manager.Final.Configuration.UserForm;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    ImageService imageService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    UserRepository userRepository;

    public void saveUser(UserForm userForm){
        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setAbout(userForm.getAbout());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());


        userRepository.save(user);
    }

    public User FindByemail(String email){

        return userRepository.findByEmail(email).orElse(null);
    }

    public String generateEmail(String email,String password){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("kaushikascm@gmail.com");
        message.setSubject("Kaushika :Otp For User Vertification");
        SecureRandom random=new SecureRandom();
        String otp=String.valueOf(1000+random.nextInt(9999));
        User user=userRepository.findByEmail(email).orElseThrow();
        if(user.getEmail().equalsIgnoreCase(email) && passwordEncoder.matches(password,user.getPassword())){
            message.setTo(email);
            message.setText("Your Otp is :"+otp);
            user.setEmailverifyotp(otp);
            userRepository.save(user);
            javaMailSender.send(message);

        }
        return "";

    }


    public void changeProfile( MultipartFile photo, Authentication authentication){
        Map<String, String> uploadData =imageService.UploadImage(photo);
        String email= Helper.getEmailofLoggedUser(authentication);
        User user=userRepository.findByEmail(email).orElseThrow();
        user.setProfilepicUrl(uploadData.get("url"));
        user.setProfilepic(uploadData.get("publicId"));
        userRepository.save(user);


    }
}
