package fbs.lg1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kunde {

    // 1. ATTRIBUTE von Kunden

    private int kundenId;
    private String name;
    private String eMail;
    private double balance;
    private boolean accountLocked;
    private int mahnungsStufe;
    private Scooter rentedScooter;
    private LocalDateTime fahrtStartZeit; // Wann die aktuelle Fahrt gestartet wurde
    private final List<Fahrt> fahrtHistorie = new ArrayList<>(); // Liste aller beendeten Fahrten

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
        this.fahrtStartZeit = null;
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
        this.fahrtStartZeit = null;
    }

    // Schritt 1: Roller ausleihen
    public boolean rollerAusleihen(Scooter scooter) {
        return rollerAusleihen(scooter, LocalDateTime.now());
    }

    // Ausleihen mit fester Startzeit
    public boolean rollerAusleihen(Scooter scooter, LocalDateTime startZeit) {
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

        // Roller zuweisen, Startzeit merken und entsperren
        this.rentedScooter = scooter;
        this.fahrtStartZeit = (startZeit != null) ? startZeit : LocalDateTime.now();
        scooter.entsperren();
        return true;
    }

    // Schritt 2: Fahrt jetzt beenden
    public boolean fahrtBeenden() {
        return fahrtBeenden(LocalDateTime.now());
    }

    // Fahrt mit Endzeit beenden
    public boolean fahrtBeenden(LocalDateTime endZeit) {
        // Prüfen ob überhaupt ein Roller ausgeliehen ist
        if (this.rentedScooter == null || this.fahrtStartZeit == null || endZeit == null) {
            return false;
        }
        if (endZeit.isBefore(this.fahrtStartZeit)) {
            return false;
        }

        // Dauer in Minuten ausrechnen
        long minuten = Duration.between(this.fahrtStartZeit, endZeit).toMinutes();

        // Fahrtkosten berechnen (0,20 € pro Minute)
        double fahrtkosten = minuten * 0.20;
        this.balance -= fahrtkosten;

        // Akku um 1% pro Minute senken
        int neuerAkku = Math.max(0, this.rentedScooter.getBattery() - (int) minuten);
        this.rentedScooter.setBattery(neuerAkku);

        // Roller wieder sperren
        this.rentedScooter.sperren();

        // Fahrt in die Historie speichern
        Fahrt fahrt = new Fahrt(this.rentedScooter, this.fahrtStartZeit, endZeit, fahrtkosten);
        this.fahrtHistorie.add(fahrt);

        // Roller zurückgeben
        this.rentedScooter = null;
        this.fahrtStartZeit = null;

        // Fällt das Guthaben unter 0 € wird der Kunde gesperrt und gemahnt
        if (this.balance < 0.0) {
            this.accountLocked = true;
            this.mahnungsStufe = 1;
            generiereMahnung();
        }

        return true;
    }

    // Alte Methode mit Minutenangabe
    public boolean fahrtBeenden(int minuten) {
        if (minuten <= 0 || this.rentedScooter == null) {
            return false;
        }
        LocalDateTime start = (this.fahrtStartZeit != null) ? this.fahrtStartZeit
                : LocalDateTime.now().minusMinutes(minuten);
        this.fahrtStartZeit = start;
        return fahrtBeenden(start.plusMinutes(minuten));
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

    public LocalDateTime getFahrtStartZeit() {
        return fahrtStartZeit;
    }

    public List<Fahrt> getFahrtHistorie() {
        return Collections.unmodifiableList(fahrtHistorie);
    }
}
