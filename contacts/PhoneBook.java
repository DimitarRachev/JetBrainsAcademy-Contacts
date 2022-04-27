package contacts;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook implements Serializable {
    List<Contact> contacts;
    File file;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public PhoneBook(List<Contact> contacts) {
        this.contacts = contacts;
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
            sb.append(i + 1).append(". ").append(contacts.get(i));
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

    public List<Contact> search(String searchTerm) {
        Pattern pattern = Pattern.compile(searchTerm);
        List<Contact> list = new ArrayList<>();
        for (Contact contact : this.contacts) {
            String s = contact.forSearch();
            Matcher matcher = pattern.matcher(s);
            if (s.toLowerCase().contains(searchTerm.toLowerCase()) || matcher.find()) {
                list.add(contact);
            }
        }
        return list;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
