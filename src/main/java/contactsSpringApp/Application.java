package contactsSpringApp;

import contactsSpringApp.ContactPack.Contact;
import contactsSpringApp.ContactPack.ContactRepository;
import contactsSpringApp.ContactPack.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ContactService contactService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Initialize DB with some values
        contactService.populateWithRandomContacts(400);
    }
}
