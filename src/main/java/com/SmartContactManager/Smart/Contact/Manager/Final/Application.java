package com.SmartContactManager.Smart.Contact.Manager.Final;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application  {



	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        String v;

        v = dotenv.get("CLOUDINARY_API_KEY");
        if (v != null) System.setProperty("CLOUDINARY_API_KEY", v);

        v = dotenv.get("CLOUDINARY_API_SECRET");
        if (v != null) System.setProperty("CLOUDINARY_API_SECRET", v);

        v = dotenv.get("CLOUDINARY_NAME");
        if (v != null) System.setProperty("CLOUDINARY_NAME", v);

        v = dotenv.get("SPRING_EMAIL_HOST");
        if (v != null) System.setProperty("SPRING_EMAIL_HOST", v);

        v = dotenv.get("SPRING_EMAIL_PORT");
        if (v != null) System.setProperty("SPRING_EMAIL_PORT", v);

        v = dotenv.get("SPRING_EMAIL_USERNAME");
        if (v != null) System.setProperty("SPRING_EMAIL_USERNAME", v);

        v = dotenv.get("SPRING_EMAIL_PASSWORD");
        if (v != null) System.setProperty("SPRING_EMAIL_PASSWORD", v);

        v = dotenv.get("SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH");
        if (v != null) System.setProperty("SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH", v);

        v = dotenv.get("SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE");
        if (v != null) System.setProperty("SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE", v);

        v = dotenv.get("GOOGLE_CLIENT_ID");
        if (v != null) System.setProperty("GOOGLE_CLIENT_ID", v);

        v = dotenv.get("GOOGLE_CLIENT_SECRET");
        if (v != null) System.setProperty("GOOGLE_CLIENT_SECRET", v);

        v = dotenv.get("SPRING_DATASOURCE_URL");
        if (v != null) System.setProperty("jdbc:mysql://mysql:3306/SmartContactManager", v);

        SpringApplication.run(Application.class, args);


	}

}
