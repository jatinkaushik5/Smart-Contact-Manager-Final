package com.SmartContactManager.Smart.Contact.Manager.Final.Repository;

import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.Contacts;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts,String> {
    Page<Contacts> getByUser(User user, Pageable pageable);
    Page<Contacts> getByNameContaining(String namekeyword,Pageable pageable);
    Page<Contacts> getByEmailContaining(String emailkeyword,Pageable pageable);
    Page<Contacts> getByPhoneNumberContaining(String phonekeyword,Pageable pageable);
    Contacts getByPhoneNumber(String phoneNumber);
}
