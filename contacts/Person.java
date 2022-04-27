package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Person extends Contact {
    private String surname;
    private String gender;
    private String birthday;

    public Person(String name, String number, LocalDateTime created, LocalDateTime edited, String surname, String gender, String birthday) {
        super(name, number, created, edited);
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    void edit(Scanner scanner) {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine();
        switch (field) {
            case "name":
                System.out.println("Enter name: ");
                super.setName(scanner.nextLine());
                break;
            case "surname":
                System.out.println("Enter surname: ");
                this.surname = scanner.nextLine();
                break;
            case "gender":
                String gender = scanner.nextLine();
                this.gender = PersonBuilder.checkGender(gender);
                if (this.gender == null) {
                    System.out.println("Bad gender!");
                }
                break;
            case "birth":
                this.birthday = scanner.nextLine();
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
        sb.append("Name: ").append(super.getName()).append(System.lineSeparator());
        sb.append("Surname: ").append(this.surname).append(System.lineSeparator());
        sb.append("Birth date: ").append(this.birthday == null ? "[no data]" : this.birthday).append(System.lineSeparator());
        sb.append("Gender: ").append(this.gender == null ? "[no data]" : this.gender).append(System.lineSeparator());
        sb.append(super.getInfo());
        return sb.toString();
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}
