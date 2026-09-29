package fbs.lg1;

public class Kunde {

    // 1. ATTRIBUTE von Kunden

    private int kundenId;
    private String name;
    private String eMail;
    private double balance;
    private boolean accountLocked;
    private int mahnungsStufe;
    private Scooter rentedScooter;

    // 2. KONSTRUKTOREN (Kunde erstellen)

    // Konstruktor für die Neuregistrierung (mindestens 10 € Startguthaben
    // erforderlich)
    public Kunde(int kundenId, String name, String eMail, double balance) {
        if (balance < 10.0) {
            throw new IllegalArgumentException("Bei der Registrierung sind mindestens 10€ Startguthaben erforderlich.");
        }
        this.kundenId = kundenId;
        this.name = name;
        this.eMail = eMail;
        this.balance = balance;
        this.accountLocked = false;
        this.mahnungsStufe = 0;
        this.rentedScooter = null;
    }

    // Konstruktor zum Laden eines bestehenden Kunden mit allen Daten
    public Kunde(int kundenId, String name, String eMail, double balance, boolean accountLocked, int mahnungsStufe) {
        this.kundenId = kundenId;
        this.name = name;
        this.eMail = eMail;
        this.balance = balance;
        this.accountLocked = accountLocked;
        this.mahnungsStufe = mahnungsStufe;
        this.rentedScooter = null;
    }

    // Schritt 1: Roller ausleihen
    public boolean rollerAusleihen(Scooter scooter) {
        if (scooter == null) {
            return false;
        }
        // Kunde darf maximal 1 Roller gleichzeitig ausleihen
        if (this.rentedScooter != null) {
            return false;
        }
        // Roller muss gesperrt sein
        if (!scooter.isLocked()) {
            return false;
        }
        // Roller muss mehr als 15% Akku haben
        if (scooter.getBattery() <= 15) {
            return false;
        }
        // Kunde braucht mindestens 1 € Guthaben
        if (this.balance < 1.0) {
            return false;
        }
        // Kunde darf nicht gesperrt sein
        if (this.accountLocked) {
            return false;
        }

        // Ausleihe erfolgreich: Roller zuweisen und entsperren
        this.rentedScooter = scooter;
        scooter.entsperren();
        return true;
    }

    // Schritt 2: Fahrt beenden (Kosten abziehen, Akku senken, Roller sperren)
    public boolean fahrtBeenden(int minuten) {
        // Fahrt muss mehr als 0 Minuten dauern und ein Roller muss ausgeliehen sein
        if (minuten <= 0 || this.rentedScooter == null) {
            return false;
        }

        // Fahrtkosten berechnen
        this.balance -= (minuten * 0.20);

        // Akku um 1% pro Minute senken
        int neuerAkku = this.rentedScooter.getBattery() - minuten;
        this.rentedScooter.setBattery(neuerAkku);

        // Roller wieder sperren und Ausleihe beenden
        this.rentedScooter.sperren();
        this.rentedScooter = null;

        // Fällt das Guthaben unter 0 € wird der Kunde gesperrt und gemahnt
        if (this.balance < 0.0) {
            this.accountLocked = true;
            this.mahnungsStufe = 1;
            generiereMahnung();
        }

        return true;
    }

    // Schritt 3: Automatische Mahnung senden
    private void generiereMahnung() {
        System.out.println("Automatische Mahnung an: " + this.eMail);
        System.out.println("Sehr geehrte(r) " + this.name + ", Ihr Konto weist ein negatives Guthaben auf.");
        System.out.println("Ihr Konto wurde gesperrt. Mahnstufe: " + this.mahnungsStufe);
    }

    // Schritt 4: Geld ein/auszahlen
    public void kontoAusgleichen(double betrag) {
        if (betrag <= 0) {
            System.out.println("Fehler: Betrag muss größer als 0 sein.");
            return;
        }

        this.balance += betrag;
        System.out.println(betrag + " € eingezahlt. Neuer Kontostand: " + this.balance + " €.");

        // Sobald das Konto wieder im Plus ist, Sperre aufheben und Mahnstatus
        // zurücksetzen
        if (this.balance > 0.0) {
            this.accountLocked = false;
            this.mahnungsStufe = 0;
            System.out.println("Konto ausgeglichen. Sperre wurde aufgehoben und Mahnstatus zurückgesetzt.");
        }
    }

    public int getKundenId() {
        return kundenId;
    }

    public void setKundenId(int kundenId) {
        this.kundenId = kundenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public int getMahnungsStufe() {
        return mahnungsStufe;
    }

    public void setMahnungsStufe(int mahnungsStufe) {
        this.mahnungsStufe = mahnungsStufe;
    }

    public Scooter getRentedScooter() {
        return rentedScooter;
    }

    public void setRentedScooter(Scooter rentedScooter) {
        this.rentedScooter = rentedScooter;
    }
}
