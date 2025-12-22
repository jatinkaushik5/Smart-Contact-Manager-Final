package com.SmartContactManager.Smart.Contact.Manager.Final.Configuration;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class UserForm {

    @NotBlank(message = "Please Fill The Name Field")
    @Size(min=2,max = 15,message = "Name Field should be between 2 and 15 ")
    private String name;
    @Email(message = "invalid Email Format")
    @NotBlank(message = "Please Fill The Email Field")
    private String email;
    @NotBlank(message = "Please fill the Password field")
    private String password;
    @NotBlank(message = "Please Field Phone Number Field")
    private String phoneNumber;
    @NotBlank(message = "Please Field About Field")
    private String about;
}
