package com.SmartContactManager.Smart.Contact.Manager.Final.Controller;


import com.SmartContactManager.Smart.Contact.Manager.Final.Configuration.Helper;
import com.SmartContactManager.Smart.Contact.Manager.Final.Configuration.UserForm;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import com.SmartContactManager.Smart.Contact.Manager.Final.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {



    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;


    @Autowired
    UserService userService;

    public String home() {
        return "Home";
    }

    @GetMapping("/login")
    public String loginPage( @RequestParam(value = "error", required = false) String error, Model model,HttpServletRequest request,
                             @RequestParam(value = "notEnabled", required = false) Boolean notEnabled) {
        if (error != null) {
            model.addAttribute("errorMsg", "Wrong Username or Password");
            request.getSession().setAttribute("formAction", "/user/authenticate");
        }

        if (Boolean.TRUE.equals(notEnabled)) {
            model.addAttribute("notEnabled", true);
            request.getSession().setAttribute("formAction", "/user/OtpSend");
           }


        // default (first visit)
        if (error == null && notEnabled == null) {
            request.getSession().setAttribute("formAction", "/user/authenticate");
        }
        return "Login";
    }



    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @GetMapping("/dashboard")
    public String Dashpage(Authentication authentication,Model model) {
        String email = Helper.getEmailofLoggedUser(authentication);
        model.addAttribute("User",userRepository.findByEmail(email).orElseThrow());
        return "Dashboard";
    }

    @GetMapping("/profile")
    public String profilepage(Model model, Authentication authentication) {
        String email = Helper.getEmailofLoggedUser(authentication);
        User user = userService.FindByemail(email);
        model.addAttribute("LoggedinUser", user);
        return "profile";
    }

    @PostMapping("/userRegister")
    public String register(@Valid @ModelAttribute UserForm userForm, BindingResult rbindingResult, Model model) {
        if (rbindingResult.hasErrors()) {
            return "register";
        }
        userService.saveUser(userForm);
        return "redirect:/user/register?success=true";
    }

    @PostMapping("/OtpSend")
    public String otpaction(@RequestParam("email")String email,@RequestParam("password")String password,HttpServletRequest request){
        userService.generateEmail(email,password);
        request.getSession().removeAttribute("formAction");
        System.out.print("Email Send");
        return "UserOtpVerify";
    }


    @PostMapping("/userotpConfirmation")
    public String showDashboardafterconfimation(@RequestParam("email")String email,@RequestParam("otp")String otp){
        User user=userService.FindByemail(email);

        if( user.getEmailverifyotp().equalsIgnoreCase(otp)){
            System.out.print("Andar");
            user.setEnabled(true);
            userRepository.save(user);
            return "redirect:/user/dashboard";
        }

        return "redirect:/user/login?notEnabled=true&otpVerify=true";
    }


    @PostMapping("/changeProfile")
    public String changeProfile(@RequestParam("userProfile")MultipartFile photo, Authentication authentication){
        userService.changeProfile(photo,authentication);
        return "redirect:/user/profile";
    }


}


//}
