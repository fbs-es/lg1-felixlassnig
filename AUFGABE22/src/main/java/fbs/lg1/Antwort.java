package fbs.lg1;

import java.time.LocalDate;

/**
 * Repräsentiert die Antwort eines Verkäufers auf eine Bewertung.
 * 
 * @invariant {@code antworttext != null && !antworttext.isBlank()}
 * @invariant {@code antwortdatum != null}
 */
public class Antwort {

    // Attribute
    private String antworttext;
    private LocalDate antwortdatum;

    /**
     * Erstellt eine neue Antwort.
     * 
     * @param antworttext der Text der Antwort
     * @param antwortdatum das Datum der Beantwortung
     * @pre {@code antworttext} ist nicht null und nicht leer
     * @pre {@code antwortdatum} ist nicht null
     * @post Antwort ist gültig erstellt
     * @throws IllegalArgumentException wenn eine Vorbedingung verletzt ist
     */
    public Antwort(String antworttext, LocalDate antwortdatum) {
        if (antworttext == null || antworttext.isBlank()) {
            throw new IllegalArgumentException("Antworttext darf nicht leer sein!");
        }
        if (antwortdatum == null) {
            throw new IllegalArgumentException("Antwortdatum darf nicht null sein!");
        }

        this.antworttext = antworttext;
        this.antwortdatum = antwortdatum;

        checkInvariant();
    }

    // Prüft, ob die Daten der Antwort gültig sind
    private void checkInvariant() {
        if (antworttext == null || antworttext.isBlank()) {
            throw new IllegalStateException("Antworttext darf nicht leer sein!");
        }
        if (antwortdatum == null) {
            throw new IllegalStateException("Antwortdatum darf nicht null sein!");
        }
    }

    // --- Getter und Setter ---

    public String getAntworttext() {
        return antworttext;
    }

    public LocalDate getAntwortdatum() {
        return antwortdatum;
    }

    public void setAntworttext(String antworttext) {
        if (antworttext == null || antworttext.isBlank()) {
            throw new IllegalArgumentException("Antworttext darf nicht leer sein!");
        }
        this.antworttext = antworttext;
        checkInvariant();
    }

    public void setAntwortdatum(LocalDate antwortdatum) {
        if (antwortdatum == null) {
            throw new IllegalArgumentException("Antwortdatum darf nicht null sein!");
        }
        this.antwortdatum = antwortdatum;
        checkInvariant();
    }
}
