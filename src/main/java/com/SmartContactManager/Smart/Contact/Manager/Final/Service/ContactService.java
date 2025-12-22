package com.SmartContactManager.Smart.Contact.Manager.Final.Service;


import com.SmartContactManager.Smart.Contact.Manager.Final.Configuration.ContactForm;
import com.SmartContactManager.Smart.Contact.Manager.Final.Configuration.Helper;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.Contacts;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.ContactsRepository;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ContactService {

    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageService imageService;
    public Contacts save(ContactForm contactForm, MultipartFile file){
        Map<String, String> uploadData =imageService.UploadImage(file);
        Contacts contacts=new Contacts();
        contacts.setName(contactForm.getContactName());
        contacts.setEmail(contactForm.getContactEmail());
        contacts.setPhoneNumber(contactForm.getContactPhone());
        contacts.setAddress(contactForm.getContactAddress());
        contacts.setDescription(contactForm.getContactDescription());
        contacts.setWeblink(contactForm.getWebLink());
        contacts.setLinkedInlink(contactForm.getLinkedinLink());
        contacts.setProfilePictureurl(uploadData.get("url"));
        contacts.setProfilePicture(uploadData.get("publicId"));
        boolean choice=contactForm.isFavourite();
        contacts.setFavourite(choice);

        return  contacts;

    }

    public void delete(String phonenumber){
        Contacts contacts=contactsRepository.getByPhoneNumber(phonenumber);
        contactsRepository.delete(contacts);
    }


    public void getbyuser(){

    }

    public Page<Contacts> getsearch(String field, String value, int size, int page, String sortBy,String order,Authentication authentication){

        Sort sort=order.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        Page<Contacts> contactsPage;
        Pageable pageable= PageRequest.of(page,size,sort);
        if(field.equalsIgnoreCase("name")){
           contactsPage =contactsRepository.getByNameContaining(value,pageable);
        }
        else if(field.equalsIgnoreCase("email")){
            contactsPage=contactsRepository.getByEmailContaining(value,pageable);
        }
        else if(field.equalsIgnoreCase("phoneNumber")){
            contactsPage=contactsRepository.getByPhoneNumberContaining(value,pageable);
        }
        else if(field.equalsIgnoreCase("all") || value.equalsIgnoreCase("")){
            String email=Helper.getEmailofLoggedUser(authentication);
            User user=userRepository.findByEmail(email).orElseThrow();
            contactsPage=contactsRepository.getByUser(user,pageable);
        }

        else{
            contactsPage=null;
        }

        return contactsPage;
    }
}
