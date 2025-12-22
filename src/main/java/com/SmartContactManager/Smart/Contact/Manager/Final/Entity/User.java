package com.SmartContactManager.Smart.Contact.Manager.Final.Entity;

import jakarta.persistence.*;
        import lombok.*;
        import org.hibernate.validator.constraints.pl.PESEL;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userid;
    private String name;
    @Column(unique = true)
    private  String email;

    @Getter(AccessLevel.NONE)
    @Column(nullable = false)
    private String password;
    private String about;
    private  String profilepic;
    @Column(unique = true)
    private String phoneNumber;

    @Getter(value = AccessLevel.NONE)
    // information
    private boolean enabled = false;

    @Column(nullable = false)
    private boolean emailVerified = false;
    @Column(nullable = false)
    private boolean phoneVerified = false;



    @ElementCollection(fetch =FetchType.EAGER)
    List<String> rolelist=new ArrayList<>();

    //    SELF,GOOGLE,GITHUB-->login
    @Enumerated(EnumType.STRING)
    private Provider provider=Provider.SELF;
    private String providerid;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contacts> contactsList=new ArrayList<>();


    private String emailverifyotp;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority>  roles=rolelist.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}


