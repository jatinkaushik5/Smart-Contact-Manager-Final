package com.SmartContactManager.Smart.Contact.Manager.Final.Controller;

import com.SmartContactManager.Smart.Contact.Manager.Final.Configuration.ContactForm;
import com.SmartContactManager.Smart.Contact.Manager.Final.Configuration.Helper;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.Contacts;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.ContactsRepository;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import com.SmartContactManager.Smart.Contact.Manager.Final.Service.ContactService;
import com.SmartContactManager.Smart.Contact.Manager.Final.Service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ContactController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    ImageService imageService;


    @Autowired
    ContactService contactService;

    @GetMapping("/contact")
    public String contactForm(){
        return "ContactPage";
    }


    @GetMapping("/Addcontact")
    public String contactFormPage(Model model,Authentication authentication){
        String email=Helper.getEmailofLoggedUser(authentication);
        model.addAttribute("contactForm",new ContactForm());
        model.addAttribute("submitted",false);
        return "ContactForm";
    }

    @GetMapping("/contacts")
    public String viewContacts(Model model, Authentication authentication, @RequestParam(value = "size",defaultValue = "10") int size,@RequestParam(value = "pagenumber",defaultValue = "0") int page,@RequestParam(value = "sortby",defaultValue = "name")String SortBy,@RequestParam(value = "direction",defaultValue = "asec")String direction){
        String email=Helper.getEmailofLoggedUser(authentication);
        User user=userRepository.findByEmail(email).orElse(null);
        Sort sort=direction.equalsIgnoreCase("desc")?Sort.by(SortBy).descending():Sort.by(SortBy).ascending();
        Pageable pageable =PageRequest.of(page,size,sort);
        Page<Contacts> contacts=contactsRepository.getByUser(user,pageable);
        model.addAttribute("contacts",contacts);
        model.addAttribute("size",size);
        return "contacts";
    }



    @PostMapping("/saveContact")
    public String save(@Valid  @ModelAttribute ContactForm contactForm, @RequestParam("multipartFile") MultipartFile image, BindingResult errorResult, Model model, Authentication authentication) throws IOException {

        if(errorResult.hasErrors()){
            System.out.print("Issue hain contact form mein");
            model.addAttribute("submitted",true);
            return "ContactForm";
        }

        String email=Helper.getEmailofLoggedUser(authentication);
        System.out.print(" contact email:"+email);
        User user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found in contact"));
        System.out.print(user.getEmail());
//        toverify is contact already saved
        Contacts contactfound =null;

        if (contactForm.getId() != null && !contactForm.getId().toString().isBlank()) {
            contactfound = contactsRepository.findById(contactForm.getId()).orElse(null);
        }

        if(contactfound==null){
            Contacts contacts =contactService.save(contactForm,image);
            contacts.setUser(user);
            contactsRepository.save(contacts);
            return "redirect:/user/Addcontact?success=true";
        }
        else {

            contactForm.setId(contactfound.getContactid());
            Contacts contacts =contactService.save(contactForm,image);
            contacts.setUser(user);
            contactsRepository.save(contacts);
            imageService.deleteimage(user,contactfound.getPhoneNumber());
            return "redirect:/user/Addcontact?success=true";
        }

    }

    @GetMapping("/contacts/search")
    public String getsearching(@RequestParam("field")String field,@RequestParam("value")String value,@RequestParam(value = "page",defaultValue = "0")int page,@RequestParam(value = "size",defaultValue = "10")int size,@RequestParam(value = "sortby",defaultValue = "name")String SortBy,@RequestParam(value = "direction",defaultValue = "asec")String direction,Model model,Authentication authentication){
        Page<Contacts> contacts=contactService.getsearch(field,value,size,page,SortBy,direction,authentication);
        model.addAttribute("contacts",contacts);
        model.addAttribute("size",size);
        return "contacts";
    }

    @GetMapping("/contacts/delete/{phonenumber}")
    public String delelecontact(@PathVariable("phonenumber") String phonenumber,Model model,Authentication authentication,@RequestParam(value = "page",defaultValue = "0")int page,@RequestParam(value = "size",defaultValue = "10")int size,@RequestParam(value = "sortby",defaultValue = "name")String SortBy,@RequestParam(value = "direction",defaultValue = "asec")String direction){
        String email=Helper.getEmailofLoggedUser(authentication);
        User user=userRepository.findByEmail(email).orElseThrow();
        Sort sort=direction.equalsIgnoreCase("desc")?Sort.by(SortBy).descending():Sort.by(SortBy).ascending();
        Pageable pageable =PageRequest.of(page,size,sort);
        Page<Contacts> contacts=contactsRepository.getByUser(user,pageable);
        Contacts contactdelete=contactsRepository.getByPhoneNumber(phonenumber);

        try{
            imageService.deleteimage(user,contactdelete.getPhoneNumber());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("contacts",contacts);
        model.addAttribute("size",size);
        contactService.delete(phonenumber);
        return "redirect:/user/contacts?success=true";
    }


    @GetMapping("/contacts/show/{phonenumber}")
    public String showcontactdetails(@PathVariable("phonenumber") String phonenumber,Model model){
        Contacts contacts=contactsRepository.getByPhoneNumber(phonenumber);
        model.addAttribute("View",contacts);
        return "contactview";
    }

    @GetMapping("/contacts/update/{phonenumber}")
    public String updatecontact(@PathVariable("phonenumber")String phonenumber,Model model){
        Contacts contact=contactsRepository.getByPhoneNumber(phonenumber);
        ContactForm form=new ContactForm();
        form.setId(contact.getContactid());
        form.setContactName(contact.getName());
        form.setContactEmail(contact.getEmail());
        form.setContactPhone(contact.getPhoneNumber());
        form.setWebLink(contact.getWeblink());
        form.setLinkedinLink(contact.getLinkedInlink());
        form.setContactDescription(contact.getDescription());
        form.setContactAddress(contact.getAddress());

        model.addAttribute("contactForm",form);
        model.addAttribute("submitted",false);
        return "ContactForm";

    }
}


