package com.SmartContactManager.Smart.Contact.Manager.Final.Configuration;


import com.SmartContactManager.Smart.Contact.Manager.Final.CustomAnnotation.FileValid;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContactForm {

    String id;

    @NotBlank(message = "please fill name field")
    @Size(min=2,max=15)
    private String contactName;
    @Email(message = "Invalid Email Format")

    private String contactEmail;
    @NotBlank(message = " please fill phone number field ")

    private String contactPhone;
    @NotBlank(message = " please fill address field ")
    private String contactAddress;
    @NotBlank(message = " please fill description field field ")
    private String contactDescription;
    private String webLink;
    private String linkedinLink;

    private boolean favourite;

}
