package fbs.lg1;

public class Scooter {

    public static final int MIN_BATTERY = 0;
    public static final int MAX_BATTERY = 100;

    private String scooterId;
    private int battery;
    private boolean locked;

    public Scooter(String scooterId, int battery, boolean locked) {
        this.scooterId = scooterId;
        setBattery(battery);
        this.locked = locked;
    }

    // Beim Erstellen vom Scooter soll 100% Akku sein und gesperrter Zustand
    public Scooter(String scooterId) {
        this(scooterId, MAX_BATTERY, true);
    }

    public void sperren() {
        setLocked(true);
    }

    public void entsperren() {
        setLocked(false);
    }

    // Ausleihen an Kunden falls möglich
    public boolean ausleihenAn(Kunde kunde) {
        return kunde != null && kunde.rollerAusleihen(this);
    }

    public String getScooterId() {
        return scooterId;
    }

    // Hier wird die scooterId gesetzt
    public void setScooterId(String scooterId) {
        this.scooterId = scooterId;
    }

    // Batteriestand abfragen
    public int getBattery() {
        return battery;
    }

    // Batteriestand setzen
    public void setBattery(int battery) {
        this.battery = Math.max(MIN_BATTERY, Math.min(MAX_BATTERY, battery));
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

}
