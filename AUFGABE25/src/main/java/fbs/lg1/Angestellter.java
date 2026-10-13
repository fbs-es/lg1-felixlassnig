package fbs.lg1;

/**
 * Repraesentiert einen fest angestellten Mitarbeiter des Unternehmens.
 * Erfasst zusaetzlich das Einstellungsjahr und den Urlaubsanspruch.
 */
public class Angestellter extends Mitarbeiter {

    // Spezifische Attribute fuer Angestellte
    private int einstellungsjahr;
    private int urlaubsanspruch; // in Arbeitstagen

    /**
     * Konstruktor fuer einen Angestellten.
     * 
     * @param vorname Vorname
     * @param nachname Nachname
     * @param gehalt Monatliches Fixgehalt
     * @param einstellungsjahr Jahr des Eintritts in das Unternehmen
     * @param urlaubsanspruch Urlaubsanspruch in Tagen pro Jahr
     */
    public Angestellter(String vorname, String nachname, double gehalt, int einstellungsjahr, int urlaubsanspruch) {
        super(vorname, nachname, gehalt);
        this.einstellungsjahr = einstellungsjahr;
        this.urlaubsanspruch = urlaubsanspruch;
    }

    // --- Getter und Setter ---

    // Gibt das Einstellungsjahr zurueck
    public int getEinstellungsjahr() {
        return einstellungsjahr;
    }

    // Setzt das Einstellungsjahr
    public void setEinstellungsjahr(int einstellungsjahr) {
        this.einstellungsjahr = einstellungsjahr;
    }

    // Gibt den Urlaubsanspruch in Tagen zurueck
    public int getUrlaubsanspruch() {
        return urlaubsanspruch;
    }

    // Setzt den Urlaubsanspruch in Tagen
    public void setUrlaubsanspruch(int urlaubsanspruch) {
        this.urlaubsanspruch = urlaubsanspruch;
    }

    /**
     * Berechnet die Betriebszugehoerigkeit in Jahren.
     * 
     * @param aktuellesJahr Das aktuelle Kalenderjahr
     * @return Anzahl der bisherigen Dienstjahre
     */
    public int berechneDienstjahre(int aktuellesJahr) {
        return Math.max(0, aktuellesJahr - einstellungsjahr);
    }

    /**
     * Passt das Gehalt prozentual an.
     * Beispiel: Bei wert = 5.0 wird das Gehalt um 5 Prozent erhoeht.
     * 
     * @param prozent Prozentualer Anpassungswert (z. B. 5.0 fuer +5%)
     */
    @Override
    public void passeGehaltAn(double prozent) {
        double neuesGehalt = getGehalt() * (1.0 + prozent / 100.0);
        setGehalt(neuesGehalt);
    }

    @Override
    public String getKategorie() {
        return "Angestellter";
    }
}
