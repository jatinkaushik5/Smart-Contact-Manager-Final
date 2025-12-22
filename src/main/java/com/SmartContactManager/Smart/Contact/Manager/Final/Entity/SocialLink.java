package com.SmartContactManager.Smart.Contact.Manager.Final.Entity;

import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.Contacts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String link;
    private String title;

    @ManyToOne
    private Contacts contacts;
}