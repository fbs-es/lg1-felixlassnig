package fbs.lg1;

import java.time.LocalDate;

public class Lieferantenbestellung {
    private int lieferantenbestellnummer;
    private int bestelleMenge;
    private LocalDate bestelldatum;
    private Produkt produkt;
    private Lieferant lieferant;

    // Konstruktor
    public Lieferantenbestellung(int lieferantenbestellnummer, Produkt produkt, int bestelleMenge) {
        this.lieferantenbestellnummer = lieferantenbestellnummer;
        this.bestelldatum = LocalDate.now();
        this.produkt = produkt;
        this.bestelleMenge = bestelleMenge;
        this.lieferant = produkt.getLieferant();
    }

    // Getter
    public int getLieferantenbestellnummer() {
        return lieferantenbestellnummer;
    }

    public LocalDate getBestelldatum() {
        return bestelldatum;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public Lieferant getLieferant() {
        return lieferant;
    }

    public int getBestellteMenge() {
        return bestelleMenge;
    }
}
