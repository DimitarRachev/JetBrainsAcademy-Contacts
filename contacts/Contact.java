package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class Contact {
    private String name;
    private String number;
    private final LocalDateTime created;
    private LocalDateTime edited;

    public Contact(String name, String number, LocalDateTime created, LocalDateTime edited) {
        this.name = name;
        this.number = number;
        this.created = created;
        this.edited = edited;
    }

    public void setName(String name) {
        this.name = name;
    }

      public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    abstract void edit(Scanner scanner);


     public String getInfo() {
         StringBuilder sb = new StringBuilder();
         sb.append("Number: ").append(getNumber() == null ? "[no data]" : getNumber()).append(System.lineSeparator());
         sb.append("Time created: ").append(getCreated()).append(System.lineSeparator());
         sb.append("Time last edit: ").append(getEdited());
         return sb.toString();
     }
}

