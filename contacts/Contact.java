package contacts;

public class Contact {
    private String name;
    private String surname;
    private String number;

    public Contact(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        if (number.equals("")) {
            return "[no number]";
        }
        return number;
    }

    @Override
    public String toString() {
        if (number.equals("")) {
            return name + " " + surname + ", [no number]";
        }
        return name + " " + surname + ", " + number;
    }
}
