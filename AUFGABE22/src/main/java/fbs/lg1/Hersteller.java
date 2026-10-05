package fbs.lg1;

/**
 * Repräsentiert den Hersteller eines Produkts.
 * 
 * @invariant {@code herstellername != null && !herstellername.isBlank()}
 * @invariant {@code supportEmail != null && supportEmail.contains("@")}
 */
public class Hersteller {

    // Attribute
    private final String herstellername;
    private final String supportEmail;

    /**
     * Erstellt einen neuen Hersteller.
     * 
     * @param herstellername der Name des Herstellers
     * @param supportEmail die Support-E-Mail-Adresse
     * @pre {@code herstellername} ist nicht null und nicht leer
     * @pre {@code supportEmail} ist nicht null und enthält ein '@'
     * @post Hersteller ist gültig erstellt
     * @throws IllegalArgumentException wenn eine Vorbedingung verletzt ist
     */
    public Hersteller(String herstellername, String supportEmail) {
        if (herstellername == null || herstellername.isBlank()) {
            throw new IllegalArgumentException("Herstellername darf nicht leer sein!");
        }
        if (supportEmail == null || supportEmail.isBlank() || !supportEmail.contains("@")) {
            throw new IllegalArgumentException("SupportEmail muss gültig sein und ein '@' enthalten!");
        }

        this.herstellername = herstellername;
        this.supportEmail = supportEmail;

        checkInvariant();
    }

    // Prüft, ob die Daten des Herstellers gültig sind
    private void checkInvariant() {
        if (herstellername == null || herstellername.isBlank()) {
            throw new IllegalStateException("Herstellername darf nicht leer sein!");
        }
        if (supportEmail == null || !supportEmail.contains("@")) {
            throw new IllegalStateException("SupportEmail ist ungültig!");
        }
    }

    // --- Getter-Methoden ---

    public String getHerstellername() {
        return herstellername;
    }

    public String getSupportEmail() {
        return supportEmail;
    }
}
