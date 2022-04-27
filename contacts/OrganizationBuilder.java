package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;

public class OrganizationBuilder {
    private String name;
    private String number;
    private LocalDateTime created;
    private LocalDateTime edited;
    String address;
    Scanner scanner;

    public OrganizationBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    Organization build() {
        System.out.println("Enter the organization name: ");
        String name = this.scanner.nextLine();
        this.name = name.length() != 0 ? name : null;
        System.out.println("Enter the address: ");
        String address = this.scanner.nextLine();
        this.address = address.length() != 0 ? address : null;
        System.out.println("Enter the number: ");
        String number = scanner.nextLine();
        if (PhoneChecker.isValid(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
        }
        this.created = LocalDateTime.now().withNano(0).withSecond(0);
        this.edited = LocalDateTime.now().withNano(0).withSecond(0);
        return new Organization(this.name, this.number, this.created, this.edited, this.address);
    }
}
