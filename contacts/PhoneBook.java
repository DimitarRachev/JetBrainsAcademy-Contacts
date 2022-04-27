package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    void add(Contact contact) {
        contacts.add(contact);
    }

    public int count() {
        return contacts.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contacts.size(); i++) {
            sb.append(i+1).append(". ").append(contacts.get(i));
            if (i < contacts.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String remove(int index) {
        try {
            contacts.remove(index);
            return "The record removed!";
        } catch (IndexOutOfBoundsException e) {
            return "No such record!";
        }
    }

}
