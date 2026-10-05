package fbs.lg1;

import java.time.LocalDate;

/**
 * Repräsentiert die Bewertung eines Kunden für ein Produkt.
 * 
 * @invariant kunde != null
 * @invariant produkt != null
 * @invariant {@code sterne >= 1 && sterne <= 5}
 * @invariant erstellungsdatum != null
 */
public class Bewertung {

    // Attribute
    private final Kunde kunde;
    private final Produkt produkt;
    private final int sterne;
    private final String kommentar;
    private final LocalDate erstellungsdatum;
    private final boolean hatGekauft;
    private Antwort antwort;

    /**
     * Erstellt eine neue Bewertung und meldet sie beim Produkt an.
     * 
     * @param kunde der bewertende Kunde
     * @param produkt das bewertete Produkt
     * @param sterne die Sterneanzahl (1 bis 5)
     * @param kommentar der persönliche Kommentar
     * @param erstellungsdatum das Datum der Bewertung
     * @param hatGekauft true, wenn der Kunde das Produkt tatsächlich gekauft hat
     * @pre {@code kunde} und {@code produkt} dürfen nicht null sein
     * @pre {@code sterne} muss zwischen 1 und 5 liegen
     * @pre {@code erstellungsdatum} darf nicht null sein
     * @post Bewertung ist gültig und beim Produkt registriert
     * @throws IllegalArgumentException wenn eine Vorbedingung verletzt ist
     */
    public Bewertung(Kunde kunde, Produkt produkt, int sterne, String kommentar, LocalDate erstellungsdatum,
            boolean hatGekauft) {
        if (kunde == null) {
            throw new IllegalArgumentException("Kunde darf nicht null sein");
        }
        if (produkt == null) {
            throw new IllegalArgumentException("Produkt darf nicht null sein");
        }
        if (sterne < 1 || sterne > 5) {
            throw new IllegalArgumentException("Sterne müssen zwischen 1 und 5 liegen");
        }
        if (kommentar == null) {
            throw new IllegalArgumentException("Kommentar darf nicht null sein");
        }
        if (erstellungsdatum == null) {
            throw new IllegalArgumentException("Erstellungsdatum darf nicht null sein");
        }

        this.kunde = kunde;
        this.produkt = produkt;
        this.sterne = sterne;
        this.kommentar = kommentar;
        this.erstellungsdatum = erstellungsdatum;
        this.hatGekauft = hatGekauft;
        this.antwort = null;

        produkt.addBewertung(this);

        checkInvariant();
    }

    // Prüft, ob alle Daten der Bewertung gültig sind
    private void checkInvariant() {
        if (kunde == null || produkt == null || erstellungsdatum == null) {
            throw new IllegalStateException("Pflichtfelder der Bewertung fehlen!");
        }
        if (sterne < 1 || sterne > 5) {
            throw new IllegalStateException("Sterne müssen zwischen 1 und 5 liegen!");
        }
    }

    /**
     * Weist der Bewertung eine Antwort zu (maximal eine erlaubt).
     * 
     * @param antwort die Antwort des Verkäufers
     * @pre {@code antwort} darf nicht null sein und es existiert noch keine Antwort
     * @post Antwort ist der Bewertung zugewiesen
     * @throws IllegalArgumentException wenn eine Vorbedingung verletzt ist
     */
    public void setAntwort(Antwort antwort) {
        if (antwort == null) {
            throw new IllegalArgumentException("Antwort darf nicht null sein!");
        }
        if (this.antwort != null) {
            throw new IllegalArgumentException("Eine Bewertung kann maximal eine Antwort erhalten!");
        }
        this.antwort = antwort;
        checkInvariant();
    }

    // --- Getter-Methoden ---

    public Antwort getAntwort() {
        return antwort;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public int getSterne() {
        return sterne;
    }

    public String getKommentar() {
        return kommentar;
    }

    public LocalDate getErstellungsdatum() {
        return erstellungsdatum;
    }

    public boolean hatGekauft() {
        return hatGekauft;
    }
}
