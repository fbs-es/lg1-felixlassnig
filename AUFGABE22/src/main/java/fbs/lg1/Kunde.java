package fbs.lg1;

/**
 * Repräsentiert einen Kunden im Shop.
 * 
 * @invariant {@code kundennummer != null && !kundennummer.isBlank()}
 * @invariant {@code name != null && !name.isBlank()}
 */
public class Kunde {

    // Attribute
    private final String kundennummer;
    private final String name;

    /**
     * Erstellt einen neuen Kunden.
     * 
     * @param kundennummer die eindeutige Kundennummer
     * @param name der Name des Kunden
     * @pre {@code kundennummer} und {@code name} sind nicht null und nicht leer
     * @post Kunde ist gültig erstellt
     * @throws IllegalArgumentException wenn eine Vorbedingung verletzt ist
     */
    public Kunde(String kundennummer, String name) {
        if (kundennummer == null || kundennummer.isBlank()) {
            throw new IllegalArgumentException("Kundennummer darf nicht leer sein!");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name darf nicht leer sein!");
        }

        this.kundennummer = kundennummer;
        this.name = name;

        checkInvariant();
    }

    // Prüft, ob alle Daten des Kunden gültig sind
    private void checkInvariant() {
        if (kundennummer == null || kundennummer.isBlank()) {
            throw new IllegalStateException("Kundennummer darf nicht leer sein!");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalStateException("Name darf nicht leer sein!");
        }
    }

    // --- Getter-Methoden ---

    public String getKundennummer() {
        return kundennummer;
    }

    public String getName() {
        return name;
    }
}
