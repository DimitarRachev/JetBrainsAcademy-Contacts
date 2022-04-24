package contacts;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        while (true) {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
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
                    break;
                case "count":
                    System.out.println("The Phone Book has " + phoneBook.count() + " records.");
                    break;
                case "list":
                    System.out.println(phoneBook);
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
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
        System.out.print("Select a field (name, surname, number): ");
        String field = scanner.nextLine();
        switch (field) {
            case "name":
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                phoneBook.editName(index, name);
                break;
            case "surname":
                System.out.println("Enter surname: ");
                String surname = scanner.nextLine();
                phoneBook.editSurname(index, surname);
                break;
            case "number":
                System.out.println("Enter number: ");
                String number = scanner.nextLine();
                if (!isValid(number)) {
                    number = "";
                    System.out.println("Wrong number format!");
                }
                phoneBook.editNumber(index, number);
                break;
        }
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
        System.out.println("Enter the name of the person:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname of the person:");
        String surname = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        if (!isValid(number)) {
            number = "";
            System.out.println("Wrong number format!");
        }
        System.out.println("The record added.");
        Contact contact = new Contact(name, surname, number);
        phoneBook.add(contact);
    }

    private static boolean isValid(String number) {
        Pattern pattern = Pattern.compile("\\+?(\\([a-zA-Z0-9]+\\)[ -][a-zA-Z0-9]{2,}|[a-zA-Z0-9]+[ -]\\([a-zA-Z0-9]{2,}\\)|[a-zA-Z0-9]+[ -][a-zA-Z0-9]{2,}|\\([a-zA-Z0-9]+\\)|[a-zA-Z0-9]+)([ -][a-zA-Z0-9]{2,})*");
        Matcher matcher = pattern.matcher(number);

        return matcher.matches();
    }
}
