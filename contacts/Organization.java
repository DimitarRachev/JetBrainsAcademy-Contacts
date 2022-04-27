package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Organization extends Contact {
    String address;

    public Organization(String name, String number, LocalDateTime created, LocalDateTime edited, String address) {
        super(name, number, created, edited);
        this.address = address;
    }


    @Override
    public String toString() {
        return getName();
    }

    @Override
    void edit(Scanner scanner) {
        System.out.print("Select a field (name, address, number): ");
        String field = scanner.nextLine();
        switch (field) {
            case "name":
                System.out.println("Enter name: ");
                super.setName(scanner.nextLine());
                break;
            case "address":
                System.out.println("Enter surname: ");
                this.address = scanner.nextLine();
                break;
            case "number":
                System.out.println("Enter number: ");
                String number = scanner.nextLine();
                if (!PhoneChecker.isValid(number)) {
                    number = null;
                    System.out.println("Wrong number format!");
                }
                super.setNumber(number);
                break;
        }
        super.setEdited(LocalDateTime.now().withNano(0).withSecond(0));
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Organization name: ").append(super.getName()).append(System.lineSeparator());
        sb.append("Address: ").append(this.address == null ? "[no data]" : this.address).append(System.lineSeparator());
        sb.append(super.getInfo());
        return sb.toString();
    }
}

