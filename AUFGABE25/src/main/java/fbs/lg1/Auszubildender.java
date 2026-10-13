package fbs.lg1;

/**
 * Repraesentiert einen Auszubildenden (Lehrling) im Unternehmen.
 * Erfasst zusaetzlich den Ausbildungsberuf und das aktuelle Ausbildungsjahr.
 */
public class Auszubildender extends Mitarbeiter {

    // Spezifische Attribute fuer Auszubildende
    private String ausbildungsberuf;
    private int aktuellesAusbildungsjahr;

    /**
     * Konstruktor fuer einen Auszubildenden.
     * 
     * @param vorname Vorname
     * @param nachname Nachname
     * @param gehalt Monatliche Lehrlingsentschaedigung
     * @param ausbildungsberuf Bezeichnung des Lehrberufs (z. B. Applikationsentwicklung)
     * @param aktuellesAusbildungsjahr Nummer des aktuellen Lehrjahrs (z. B. 1, 2, 3 oder 4)
     */
    public Auszubildender(String vorname, String nachname, double gehalt, String ausbildungsberuf, int aktuellesAusbildungsjahr) {
        super(vorname, nachname, gehalt);
        this.ausbildungsberuf = ausbildungsberuf;
        this.aktuellesAusbildungsjahr = aktuellesAusbildungsjahr;
    }

    // --- Getter und Setter ---

    // Gibt den Ausbildungsberuf zurueck
    public String getAusbildungsberuf() {
        return ausbildungsberuf;
    }

    // Setzt den Ausbildungsberuf
    public void setAusbildungsberuf(String ausbildungsberuf) {
        this.ausbildungsberuf = ausbildungsberuf;
    }

    // Gibt das aktuelle Ausbildungsjahr zurueck
    public int getAktuellesAusbildungsjahr() {
        return aktuellesAusbildungsjahr;
    }

    // Setzt das aktuelle Ausbildungsjahr
    public void setAktuellesAusbildungsjahr(int aktuellesAusbildungsjahr) {
        this.aktuellesAusbildungsjahr = aktuellesAusbildungsjahr;
    }

    /**
     * Schaltet den Auszubildenden in das naechste Ausbildungsjahr
     * und erhoeht das Gehalt um einen festen Betrag.
     * 
     * @param betragsErhoehung Fester Betrag, um den das Gehalt steigt
     */
    public void rueckeInNaechstesLehrjahr(double betragsErhoehung) {
        this.aktuellesAusbildungsjahr++;
        passeGehaltAn(betragsErhoehung);
    }

    /**
     * Passt das Gehalt des Auszubildenden um einen festen Pauschalbetrag an.
     * 
     * @param fixerBetrag Fester Euro-Betrag (z. B. 100 EUR)
     */
    @Override
    public void passeGehaltAn(double fixerBetrag) {
        setGehalt(getGehalt() + fixerBetrag);
    }

    @Override
    public String getKategorie() {
        return "Auszubildender";
    }
}
