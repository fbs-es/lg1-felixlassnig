package fbs.lg1;

public class Nutzer {
    // Attrribute
    private int userId;
    private String name;
    private String eMail;

    // Konstruktor von Nutzer
    public Nutzer(int userId, String name, String eMail) {
        this.userId = userId;
        this.name = name;
        this.eMail = eMail;
    }

    // Getter

    // Hier wird die UserId geholt
    public int getUserId() {
        return userId;
    }

    // Hier wird der Name geholt
    public String getName() {
        return name;
    }

    // Hier wird die EMail geholt
    public String getEmail() {
        return eMail;
    }
}
