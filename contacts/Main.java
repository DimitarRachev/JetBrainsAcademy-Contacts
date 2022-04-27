package contacts;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String command = scanner.nextLine();
            switch (command) {
                case "add":
                    addPerson(scanner, phoneBook);
                    break;
                //TODO implement remove and edit, after completion of the project
//                case "remove":
//                    removePerson(scanner, phoneBook);
//                    break;
//                case "edit":
//                    editPerson(scanner, phoneBook);
//                    System.out.println("The record updated!");
//                    break;
                case "list":
                    listMenu(scanner, phoneBook);
                    break;
                case "search":
                    mainSearch(scanner, phoneBook);
                    break;
//                case "info":
//                    printInfo(scanner, phoneBook);
//                    break;
                case "count":
                    System.out.println("The Phone Book has " + phoneBook.count() + " records.");
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
            System.out.println();
        }
    }

    private static void listMenu(Scanner scanner, PhoneBook phoneBook) {
        System.out.println(phoneBook);
        System.out.println();
        System.out.println("[list] Enter action ([number], back): ");
        String command = scanner.nextLine();
        switch (command) {
            case "back":
                return;
            default:
                int index = Integer.parseInt(command) - 1;
                Contact contact = phoneBook.contacts.get(index);
                System.out.println(contact.getInfo());
                System.out.println();
                recordHandling(scanner, contact, phoneBook, index);
                break;
        }
    }

    private static void mainSearch(Scanner scanner, PhoneBook phoneBook) {
        System.out.println("Enter search query: ");
        String searchTerm = scanner.nextLine();
        ;
        List<Contact> temp = phoneBook.search(searchTerm);
        PhoneBook tempBook = new PhoneBook(temp);
        System.out.println("Found " + tempBook.count() + " results:");
        System.out.println(tempBook);
        System.out.println();
        searchMenu(tempBook, scanner, phoneBook);
    }

    private static void searchMenu(PhoneBook tempBook, Scanner scanner, PhoneBook phoneBook) {
        System.out.println("[search] Enter action ([number], back, again): ");
        String action = scanner.nextLine();
        switch (action) {
            case "back":
                return;
            case "again":
                mainSearch(scanner, phoneBook);
                break;
            default:
                int index = Integer.parseInt(action) - 1;
                Contact contact = tempBook.contacts.get(index);
                System.out.println(contact.getInfo());
                System.out.println();
                recordHandling(scanner, contact, phoneBook, index);
        }
    }

    private static void recordHandling(Scanner scanner, Contact contact, PhoneBook phoneBook, int index) {
        System.out.println("[record] Enter action (edit, delete, menu): ");
        String command = scanner.nextLine();
        switch (command) {
            case "delete":
                phoneBook.contacts.remove(index);
                break;
            case "menu":
                return;
            case "edit":
                contact.edit(scanner);
                System.out.println("Saved");
                System.out.println(contact.getInfo());
                System.out.println();
                recordHandling(scanner, contact, phoneBook, index);
                break;
        }
    }

    private static void printInfo(Scanner scanner, PhoneBook phoneBook) {
        System.out.println(phoneBook);
        System.out.println("Enter index to show info: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Contact contact = phoneBook.contacts.get(index);
        System.out.println(contact.getInfo());
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
