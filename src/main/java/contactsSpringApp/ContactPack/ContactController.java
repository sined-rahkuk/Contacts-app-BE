package contactsSpringApp.ContactPack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin()
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/contacts", produces = "application/json")
    public ResponseEntity<List<Contact>> getAllContacts(@RequestParam(required = false) String chunk, @RequestParam(required = false) String amount) {
//    public <T> ResponseEntity <T> getAllContacts(@RequestParam(required = false) String chunk, @RequestParam(required = false) String amount) {

        // FIXME: How to return error's text in response body if I'm tied on return value type?
        //  I assume it's got to be something with generics return-type-magic

        try {
            List<Contact> result = this.contactService.getPart(chunk, amount);
            return new ResponseEntity<List<Contact>>(result, HttpStatus.OK);
        } catch (NumberFormatException nfe) {

            // FIXME: Change body to tell FrontEnd what's the cause, http status itself is not enough I guess

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//            return new ResponseEntity<String>(nfe.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping("/contacts/{id}")
    public Contact getContact(@PathVariable String id) {
        return this.contactService.getContact(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contacts")
    public void addContact(@RequestBody Contact contact) {
        System.out.println("POST REQUEST " + new Date().toString() + contact.toString());
        this.contactService.addContact(contact);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/contacts/{id}")
    public void addContact(@RequestBody Contact contact, @PathVariable String id) {
        this.contactService.updateContact(id, contact);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/contacts/{id}")
    public void deleteUser(@PathVariable String id) {
        this.contactService.deleteUser(id);
    }
}
