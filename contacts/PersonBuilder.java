package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PersonBuilder {
    private String name;
    private String number;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String surname;
    private String gender;
    private String birthday;
    Scanner scanner;

    public PersonBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    Person build() {
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();
        this.name = name.length() != 0 ? name : null;
        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();
        this.surname = surname.length() != 0 ? surname : null;
        System.out.println("Enter the birth date: ");
        String birthday = scanner.nextLine();
        if (this.birthday == null) {
            System.out.println("Bad birth date!");
        }
        this.birthday = birthday.length() != 0 ? birthday : null;
        System.out.println("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        this.gender = checkGender(gender);
        if (this.gender == null) {
            System.out.println("Bad gender!");
        }
        System.out.println("Enter the number: ");
        String number = scanner.nextLine();
        if (PhoneChecker.isValid(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
        }
        this.created = LocalDateTime.now().withNano(0).withSecond(0);
        this.edited = LocalDateTime.now().withNano(0).withSecond(0);
        return new Person(this.name, this.number, this.created, this.edited, this.surname, this.gender, this.birthday);
    }

    public static String checkGender(String gender) {
        switch (gender) {
            case "M":
            case "F":
                return gender;
            default:
                return null;
        }
    }
}
