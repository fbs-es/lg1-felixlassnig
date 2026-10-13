package fbs.lg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repraesentiert eine flexible Aushilfskraft im Unternehmen.
 * Erfasst einen Stundenlohn und eine Liste aller geleisteten Arbeitszeiten.
 */
public class Aushilfe extends Mitarbeiter {

    // Spezifische Attribute fuer Aushilfen
    private double stundenlohn;
    private final List<Arbeitszeit> arbeitszeiten;

    /**
     * Konstruktor fuer eine Aushilfe.
     * 
     * @param vorname Vorname
     * @param nachname Nachname
     * @param gehalt Bisheriges bzw. Grundgehalt
     * @param stundenlohn Vergütung pro geleisteter Arbeitsstunde in Euro
     */
    public Aushilfe(String vorname, String nachname, double gehalt, double stundenlohn) {
        super(vorname, nachname, gehalt);
        this.stundenlohn = stundenlohn;
        this.arbeitszeiten = new ArrayList<>();
    }

    // --- Getter und Setter ---

    // Gibt den aktuellen Stundenlohn zurueck
    public double getStundenlohn() {
        return stundenlohn;
    }

    // Setzt den Stundenlohn
    public void setStundenlohn(double stundenlohn) {
        this.stundenlohn = stundenlohn;
    }

    // Gibt eine unveraenderliche Ansicht der erfassten Arbeitszeiten zurueck
    public List<Arbeitszeit> getArbeitszeiten() {
        return Collections.unmodifiableList(arbeitszeiten);
    }

    /**
     * Fuegt einen neuen Arbeitszeiteintrag zur Dokumentation hinzu.
     * 
     * @param arbeitszeit Die geleistete Arbeitszeit
     */
    public void arbeitszeitHinzufuegen(Arbeitszeit arbeitszeit) {
        this.arbeitszeiten.add(arbeitszeit);
    }

    /**
     * Berechnet die Summe aller noch nicht abgerechneten Arbeitsminuten.
     * 
     * @return Offene Arbeitsminuten
     */
    public int getOffeneArbeitsminuten() {
        int summe = 0;
        for (Arbeitszeit az : arbeitszeiten) {
            if (!az.isAbgerechnet()) {
                summe += az.getZeitInMinuten();
            }
        }
        return summe;
    }

    /**
     * Berechnet die noch offenen Arbeitsstunden als Dezimalwert.
     * 
     * @return Offene Arbeitsstunden
     */
    public double getOffeneArbeitsstunden() {
        return getOffeneArbeitsminuten() / 60.0;
    }

    /**
     * Rechnet alle offenen Arbeitszeiten ab.
     * Berechnet den Betrag anhand des Stundenlohns, addiert ihn zum Gehalt
     * und markiert die Eintraege als abgerechnet.
     * 
     * @return Der in dieser Abrechnung ausgezahlte Betrag
     */
    public double rechneOffeneArbeitszeitenAb() {
        double auszahlung = 0.0;
        for (Arbeitszeit az : arbeitszeiten) {
            if (!az.isAbgerechnet()) {
                double stunden = az.getZeitInStunden();
                auszahlung += stunden * stundenlohn;
                az.alsAbgerechnetMarkieren();
            }
        }
        setGehalt(getGehalt() + auszahlung);
        return auszahlung;
    }

    /**
     * Gehaltsanpassung fuer eine Aushilfe:
     * Rechnet saemtliche bisher erfassten offenen Arbeitsminuten ab.
     * Wenn ein neuer Stundenlohn (> 0) uebergeben wird, wird dieser vorher aktualisiert.
     * 
     * @param neuerStundenlohn Neuer Stundenlohn (oder 0, falls bestehender genutzt werden soll)
     */
    @Override
    public void passeGehaltAn(double neuerStundenlohn) {
        if (neuerStundenlohn > 0) {
            this.stundenlohn = neuerStundenlohn;
        }
        rechneOffeneArbeitszeitenAb();
    }

    @Override
    public String getKategorie() {
        return "Aushilfe";
    }
}
