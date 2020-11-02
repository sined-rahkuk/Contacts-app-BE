package contactsSpringApp.ContactPack;

import contactsSpringApp.ContactPack.ValuesGenerating.Names;
import contactsSpringApp.ContactPack.ValuesGenerating.RandomString;
import contactsSpringApp.ContactPack.ValuesGenerating.TelNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public static void main(String[] args) {
        ContactService svc = new ContactService();

        svc.populateWithRandomContacts(10);
    }

    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        this.contactRepository.findAll().forEach(contacts::add);
        return contacts;
    }

    public List<Contact> getPart(String chunk, String amount) throws NumberFormatException {

        // May throw NumberFormatException because of null
        int chunkInt = Integer.parseInt(chunk);
        int amountInt = Integer.parseInt(amount);

        if (chunkInt < 1) throw new NumberFormatException("chunk should be greater or equal 1");
        if (amountInt < 1) throw new NumberFormatException("amount should be greater or equal 1");

/*
         TODO: I'm sure there is a way to not explicitly skip
          contactsToSkip-elements in iterable in order to get to the required chunk in sequence
*/
        List<Contact> contacts = new ArrayList<Contact>();
        int contactsToSkip = amountInt * (chunkInt - 1);
        int counter = 0;

        for (Contact contact :
                this.contactRepository.findAll()) {
            if (counter++ < contactsToSkip) continue;

            if (counter - contactsToSkip <= amountInt) {
                contacts.add(contact);
            }

        }
        return contacts;

    }

    public Contact getContact(String id) {
        return contactRepository.findById(id).get();
    }

    public void addContact(Contact contact) {
        if (contact.getId() == null) {
            contact.setId(RandomString.generate(6));
        }
        contactRepository.save(contact);

    }

    public void deleteUser(String id) {
        contactRepository.deleteById(id);
    }

    public void updateContact(String id, Contact contact) {
        contactRepository.save(contact);
    }

    public void populateWithRandomContacts(int amount) {

        if (amount < 1) return;
        Names names = new Names();
        TelNumberGenerator telGen = new TelNumberGenerator();


        List<Contact> listToBeSaved = new ArrayList<>(amount);

        for (int i = 0; i < amount; i++) {
            Contact contact = new Contact(
                    RandomString.generate(6),
                    names.getRandomFirstName(),
                    names.getRandomLastName(),
                    telGen.generateTelNumber(),
                    "Sample of description"
            );
            listToBeSaved.add(contact);
        }
        contactRepository.saveAll(listToBeSaved);

    }

}
