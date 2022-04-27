package contacts;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        while (true) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            String command = scanner.nextLine();
            switch (command) {
                case "add":
                    addPerson(scanner, phoneBook);
                    break;
                case "remove":
                    removePerson(scanner, phoneBook);
                    break;
                case "edit":
                    editPerson(scanner, phoneBook);
                    System.out.println("The record updated!");
                    break;
                case "count":
                    System.out.println("The Phone Book has " + phoneBook.count() + " records.");
                    break;
                case "info":
                    System.out.println(phoneBook);
                    System.out.println("Enter index to show info: ");
                    int index = Integer.parseInt(scanner.nextLine()) - 1;
                    Contact contact = phoneBook.contacts.get(index);
                    System.out.println(contact.getInfo());
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
            System.out.println();
        }
    }

    private static void editPerson(Scanner scanner, PhoneBook phoneBook) {
        if (phoneBook.count() == 0) {
            System.out.println("No records to edit!");
            return;
        }
        System.out.println(phoneBook);
        System.out.print("Select a record: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Contact contact = phoneBook.contacts.get(index);
        contact.edit(scanner);
    }

    private static void removePerson(Scanner scanner, PhoneBook phoneBook) {
        if (phoneBook.count() == 0) {
            System.out.println("No records to remove!");
            return;
        }
        System.out.println(phoneBook);
        System.out.print("Select a record: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.println(phoneBook.remove(index));
    }

    private static void addPerson(Scanner scanner, PhoneBook phoneBook) {
        try {
            Contact contact = ContactFactory.makeContact(scanner);
            phoneBook.add(contact);
            System.out.println("The record added.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
