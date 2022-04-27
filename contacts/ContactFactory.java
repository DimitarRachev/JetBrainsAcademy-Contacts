package contacts;

import java.util.Scanner;

public class ContactFactory {
    static Contact makeContact(Scanner scanner) {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();
        switch (type) {
            case "person":
               return new PersonBuilder(scanner).build();
            case "organization":
                return new OrganizationBuilder(scanner).build();
            default:
                throw new IllegalArgumentException("No such type contacts:" + type);
        }
    }
}
