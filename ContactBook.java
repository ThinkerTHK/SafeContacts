import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactBook {
    private List<Contact> contacts;

    public ContactBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public boolean removeContactByName(String name) {
        return contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
    }

    public List<Contact> findContactsByName(String name) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(contact);
            }
        }
        return contacts;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }


    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact: contacts) {
                String line = contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail();
                writer.write(line);
                writer.newLine();
            }
        }
    }


    public void loadFromFile(String filename) throws IOException {
        contacts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                }
            }
        }
    }

}
