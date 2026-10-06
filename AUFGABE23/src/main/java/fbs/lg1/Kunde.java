package fbs.lg1;

import java.util.ArrayList;
import java.util.List;

public class Kunde {
    // kundennummer, name, eMail und Liste von Bestellungen festlegen.
    private int kundennummer;
    private String name;
    private String eMail;
    private List<Bestellung> bestellungen;

    // Konstruktor für Kunde
    public Kunde(int kundennummer, String name, String eMail) {
        this.kundennummer = kundennummer;
        this.name = name;
        this.eMail = eMail;
        this.bestellungen = new ArrayList<>();
    }

    // Getter
    public int getKundennummer() {
        return kundennummer;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return eMail;
    }

    public List<Bestellung> getBestellungen() {
        return bestellungen;
    }

    public void addBestellung(Bestellung bestellung) {
        this.bestellungen.add(bestellung);
    }
}
