package com.SmartContactManager.Smart.Contact.Manager.Final.CustomAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<FileValid, MultipartFile> {
    private  static final long max_file_size=1024*1024*5;
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        if(file==null ||file.isEmpty() )
        {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File Cannot be empty").addConstraintViolation();
            return false;
        }


        if(file.getSize()>max_file_size){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less than 5 mb").addConstraintViolation();
            return false;
        }
        return true;
    }

}
