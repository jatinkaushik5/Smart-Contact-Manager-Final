package com.SmartContactManager.Smart.Contact.Manager.Final.Repository;

import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository  extends JpaRepository<User,String> {

    Optional<User> findByEmail(String s);

}
