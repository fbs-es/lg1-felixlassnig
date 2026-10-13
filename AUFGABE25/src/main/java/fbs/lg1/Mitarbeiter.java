package fbs.lg1;

/**
 * Abstrakte Basisklasse fuer alle Beschaeftigten der Mitarbeiterverwaltung.
 * Speichert allgemeine Stammdaten wie Vorname, Nachname und Gehalt.
 */
public abstract class Mitarbeiter {

    // Allgemeine Attribute fuer alle Beschaeftigten
    private String vorname;
    private String nachname;
    private double gehalt;

    /**
     * Konstruktor zur Initialisierung eines Mitarbeiters.
     * 
     * @param vorname Vorname des Mitarbeiters
     * @param nachname Nachname des Mitarbeiters
     * @param gehalt Grundgehalt des Mitarbeiters
     */
    public Mitarbeiter(String vorname, String nachname, double gehalt) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.gehalt = gehalt;
    }

    // --- Getter und Setter ---

    // Gibt den Vornamen zurueck
    public String getVorname() {
        return vorname;
    }

    // Setzt den Vornamen
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    // Gibt den Nachnamen zurueck
    public String getNachname() {
        return nachname;
    }

    // Setzt den Nachnamen
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    // Gibt das aktuelle Gehalt zurueck
    public double getGehalt() {
        return gehalt;
    }

    // Setzt das aktuelle Gehalt
    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    // Gibt den vollstaendigen Namen (Vorname + Nachname) zurueck
    public String getVollerName() {
        return vorname + " " + nachname;
    }

    /**
     * Abstrakte Methode zur Gehaltsanpassung.
     * Jede Unterklasse implementiert ihre eigene Berechnungslogik.
     * 
     * @param wert Spezifischer Anpassungswert (z. B. Prozent oder Betrag)
     */
    public abstract void passeGehaltAn(double wert);

    /**
     * Gibt die Kategorie des Beschaeftigten als Text zurueck (z. B. Angestellter).
     * 
     * @return Kategoriebezeichnung
     */
    public abstract String getKategorie();

    @Override
    public String toString() {
        return String.format("%s: %s (Gehalt: %.2f EUR)", getKategorie(), getVollerName(), gehalt);
    }
}
