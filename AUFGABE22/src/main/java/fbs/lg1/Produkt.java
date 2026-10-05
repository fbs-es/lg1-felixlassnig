package fbs.lg1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repräsentiert ein Produkt im Shop.
 * 
 * Invariante: Nummer und Name nicht leer, Preis > 0, Hersteller vorhanden,
 * alle Bewertungen gehören zu diesem Produkt.
 */
public class Produkt {

    // Attribute
    private final String produktNummer;
    private final String name;
    private final BigDecimal preis;
    private final Hersteller hersteller;
    private final List<Bewertung> bewertungen = new ArrayList<>();

    /**
     * Erstellt ein neues Produkt.
     *
     * @pre produktNummer und name nicht leer, preis > 0, hersteller != null
     * @post Produkt ist gültig initialisiert
     * @throws IllegalArgumentException wenn Daten ungültig sind
     */
    public Produkt(String produktNummer, String name, BigDecimal preis, Hersteller hersteller) {
        if (produktNummer == null || produktNummer.isBlank()) {
            throw new IllegalArgumentException("Produktnummer darf nicht leer sein!");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Produktname darf nicht leer sein!");
        }
        if (preis == null || preis.signum() <= 0) {
            throw new IllegalArgumentException("Preis muss größer als 0 sein!");
        }
        if (hersteller == null) {
            throw new IllegalArgumentException("Hersteller darf nicht fehlen!");
        }

        this.produktNummer = produktNummer;
        this.name = name;
        this.preis = preis;
        this.hersteller = hersteller;

        checkInvariant();
    }

    // Prüft, ob alle Daten des Produkts gültig sind
    private void checkInvariant() {
        if (produktNummer == null || produktNummer.isBlank()) {
            throw new IllegalStateException("Produktnummer darf nicht leer sein!");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Produktname darf nicht leer sein!");
        }
        if (preis == null || preis.signum() <= 0) {
            throw new IllegalStateException("Preis muss größer als 0 sein!");
        }
        if (hersteller == null) {
            throw new IllegalStateException("Hersteller darf nicht fehlen!");
        }
        for (Bewertung bewertung : bewertungen) {
            if (bewertung.getProdukt() != this) {
                throw new IllegalStateException("Bewertung gehört zu einem anderen Produkt!");
            }
        }
    }

    /**
     * Fügt eine Bewertung zum Produkt hinzu.
     *
     * @pre bewertung != null und gehört zu diesem Produkt
     * @post Bewertung ist in der Liste gespeichert
     */
    public void addBewertung(Bewertung bewertung) {
        if (bewertung == null) {
            throw new IllegalArgumentException("Bewertung darf nicht null sein!");
        }
        if (bewertung.getProdukt() != this) {
            throw new IllegalArgumentException("Bewertung ist nicht diesem Produkt zugeordnet");
        }

        if (!this.bewertungen.contains(bewertung)) {
            this.bewertungen.add(bewertung);
        }
        checkInvariant();
    }

    /**
     * Berechnet den Durchschnitt aller Sternebewertungen.
     *
     * @return Durchschnitt der Sterne oder 0.0 wenn keine Bewertungen da sind
     */
    public double berechneDurchschnittssterne() {
        if (bewertungen.isEmpty()) {
            return 0.0;
        }
        double summe = 0.0;
        for (Bewertung b : bewertungen) {
            summe += b.getSterne();
        }
        return summe / bewertungen.size();
    }

    // --- Getter-Methoden ---

    public String getProduktNummer() {
        return produktNummer;
    }

    public String getProduktName() {
        return name;
    }

    public BigDecimal getProduktPreis() {
        return preis;
    }

    public Hersteller getHersteller() {
        return hersteller;
    }

    public List<Bewertung> getBewertungen() {
        return Collections.unmodifiableList(bewertungen);
    }
}
