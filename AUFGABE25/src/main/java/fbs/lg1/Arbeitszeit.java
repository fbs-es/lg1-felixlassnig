package fbs.lg1;

import java.time.LocalDate;

/**
 * Dokumentiert eine geleistete Arbeitszeit einer Aushilfskraft.
 * Erfasst das Datum, die Dauer in Minuten sowie den Abrechnungsstatus.
 */
public class Arbeitszeit {

    // Attribute der Arbeitszeit
    private LocalDate datum;
    private int zeitInMinuten;
    private boolean abgerechnet;

    /**
     * Standard-Konstruktor: Eine neu erfasste Arbeitszeit ist zunaechst noch nicht abgerechnet.
     * 
     * @param datum Datum des Arbeitseinsatzes
     * @param zeitInMinuten Geleistete Arbeitsdauer in Minuten
     */
    public Arbeitszeit(LocalDate datum, int zeitInMinuten) {
        this(datum, zeitInMinuten, false);
    }

    /**
     * Vollstaendiger Konstruktor mit Abrechnungsstatus.
     * 
     * @param datum Datum des Arbeitseinsatzes
     * @param zeitInMinuten Geleistete Arbeitsdauer in Minuten
     * @param abgerechnet Gibt an, ob diese Arbeitszeit bereits abgerechnet wurde
     */
    public Arbeitszeit(LocalDate datum, int zeitInMinuten, boolean abgerechnet) {
        this.datum = datum;
        this.zeitInMinuten = zeitInMinuten;
        this.abgerechnet = abgerechnet;
    }

    // --- Getter und Setter ---

    // Gibt das Datum des Einsatzes zurueck
    public LocalDate getDatum() {
        return datum;
    }

    // Setzt das Datum des Einsatzes
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    // Gibt die Arbeitsdauer in Minuten zurueck
    public int getZeitInMinuten() {
        return zeitInMinuten;
    }

    // Setzt die Arbeitsdauer in Minuten
    public void setZeitInMinuten(int zeitInMinuten) {
        this.zeitInMinuten = zeitInMinuten;
    }

    // Gibt an, ob die Arbeitszeit bereits abgerechnet wurde
    public boolean isAbgerechnet() {
        return abgerechnet;
    }

    // Setzt den Abrechnungsstatus
    public void setAbgerechnet(boolean abgerechnet) {
        this.abgerechnet = abgerechnet;
    }

    /**
     * Rechnet die Minuten in Stunden (als Dezimalwert) um.
     * 
     * @return Dauer in Stunden (z. B. 90 Minuten -> 1.5 Stunden)
     */
    public double getZeitInStunden() {
        return zeitInMinuten / 60.0;
    }

    /**
     * Markiert die Arbeitszeit als erfolgreich abgerechnet.
     */
    public void alsAbgerechnetMarkieren() {
        this.abgerechnet = true;
    }

    @Override
    public String toString() {
        return String.format("Arbeitszeit vom %s: %d Min. (%.2f h) - %s",
                datum, zeitInMinuten, getZeitInStunden(), abgerechnet ? "abgerechnet" : "offen");
    }
}
