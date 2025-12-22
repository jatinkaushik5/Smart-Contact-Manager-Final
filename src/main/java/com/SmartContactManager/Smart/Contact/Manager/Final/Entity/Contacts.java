package com.SmartContactManager.Smart.Contact.Manager.Final.Entity;

import jakarta.persistence.*;
import lombok.*;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.SocialLink;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String contactid;
    private String name;
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String profilePicture;
    private String profilePictureurl;
    private String Address;
    private String description;
    private boolean favourite=false;
    private String Weblink;
    private String LinkedInlink;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contacts",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SocialLink> socialLinks=new ArrayList<>();

}


