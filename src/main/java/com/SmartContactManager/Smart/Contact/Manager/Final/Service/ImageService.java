package com.SmartContactManager.Smart.Contact.Manager.Final.Service;


import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.Contacts;
import com.SmartContactManager.Smart.Contact.Manager.Final.Entity.User;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.ContactsRepository;
import com.SmartContactManager.Smart.Contact.Manager.Final.Repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactsRepository contactsRepository;
    @Autowired
    Cloudinary cloudinary;

    public ImageService(Cloudinary cloudinary){
        this.cloudinary=cloudinary;
    }

    public Map<String,String> UploadImage(MultipartFile file){
        String publicId=UUID.randomUUID().toString();

        try {
            byte[] data=file.getBytes();
            Map upload=cloudinary.uploader().upload(data,ObjectUtils.asMap(
                    "public_id",publicId
            ));

            Map<String, String> result = new HashMap<>();
            result.put("publicId", publicId);
            result.put("url", upload.get("secure_url").toString());

            return result;


        } catch (IOException e) {
            throw new RuntimeException(e);

        }


    }

    @Transactional
    public void deleteimage(User user,String phonenumber) throws IOException {
        Contacts contact=contactsRepository.getByPhoneNumber(phonenumber);
        String publicid=contact.getProfilePicture();
        cloudinary.uploader().destroy(publicid,Map.of());

    }

    public String getUrlfrompublicId(String publicid) {
        return cloudinary
                .url()
                .transformation(new Transformation<>().width(500).height(500).crop("fill"))
                .generate(publicid);
    }
}
