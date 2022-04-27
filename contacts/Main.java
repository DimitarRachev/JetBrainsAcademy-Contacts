package contacts;

import java.io.*;
import java.util.List;
import java.util.Scanner;


public class Main {

   static ObjectOutputStream outStream = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        File file = null;
        if (args.length != 0) {
            file = new File(args[0]);
        }
        PhoneBook phoneBook;
        if (file == null) {
            phoneBook = new PhoneBook();
        } else {
            outStream = new ObjectOutputStream(new FileOutputStream(file));
            phoneBook = (PhoneBook) new ObjectInputStream(new BufferedInputStream(new FileInputStream(file))).readObject();
        }

        phoneBook.setFile(file);
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String command = scanner.nextLine();
            switch (command) {
                case "add":
                    addPerson(scanner, phoneBook);
                    saveContacts(outStream, phoneBook);
                    break;
                case "list":
                    listMenu(scanner, phoneBook);
                    break;
                case "search":
                    mainSearch(scanner, phoneBook);
                    break;
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

    private static void saveContacts(ObjectOutputStream outStream, PhoneBook phoneBook) throws IOException {
        if (outStream != null) {
            outStream.writeObject(phoneBook);
        }
    }

    private static void listMenu(Scanner scanner, PhoneBook phoneBook) throws IOException {
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

    private static void mainSearch(Scanner scanner, PhoneBook phoneBook) throws IOException {
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

    private static void searchMenu(PhoneBook tempBook, Scanner scanner, PhoneBook phoneBook) throws IOException {
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

    private static void recordHandling(Scanner scanner, Contact contact, PhoneBook phoneBook, int index) throws IOException {
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
                saveContacts(outStream, phoneBook);
                System.out.println(contact.getInfo());
                System.out.println();
                recordHandling(scanner, contact, phoneBook, index);
                break;
        }
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
