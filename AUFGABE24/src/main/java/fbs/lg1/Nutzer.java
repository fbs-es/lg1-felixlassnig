package fbs.lg1;

public class Nutzer {
    private int userId;
    private String name;
    private String eMail;

    public Nutzer(int userId, String name, String eMail) {
        this.userId = userId;
        this.name = name;
        this.eMail = eMail;
    }

    // Getter

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return eMail;
    }
}
