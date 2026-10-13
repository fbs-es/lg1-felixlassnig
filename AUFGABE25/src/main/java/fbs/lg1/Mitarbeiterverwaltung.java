package fbs.lg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Verwaltungsklasse fuer alle Beschaeftigten der ABC GmbH (bzw. Lassnig IT Solutions).
 * Verwaltet Angestellte, Auszubildende und Aushilfen in einer zentralen Liste.
 */
public class Mitarbeiterverwaltung {

    // Attribute
    private String firmenname;
    private final List<Mitarbeiter> mitarbeiterListe;

    /**
     * Standard-Konstruktor mit Standard-Firmennamen aus Kaernten.
     */
    public Mitarbeiterverwaltung() {
        this("Lassnig IT Solutions Klagenfurt");
    }

    /**
     * Konstruktor mit individuellem Firmennamen.
     * 
     * @param firmenname Name des Unternehmens
     */
    public Mitarbeiterverwaltung(String firmenname) {
        this.firmenname = firmenname;
        this.mitarbeiterListe = new ArrayList<>();
    }

    // --- Getter und Setter ---

    // Gibt den Firmennamen zurueck
    public String getFirmenname() {
        return firmenname;
    }

    // Setzt den Firmennamen
    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    // Gibt eine unveraenderliche Ansicht der Mitarbeiterliste zurueck
    public List<Mitarbeiter> getMitarbeiterListe() {
        return Collections.unmodifiableList(mitarbeiterListe);
    }

    // Gibt die Anzahl der aktuell registrierten Mitarbeiter zurueck
    public int getAnzahlMitarbeiter() {
        return mitarbeiterListe.size();
    }

    /**
     * Fuegt einen neuen Mitarbeiter zur Verwaltung hinzu.
     * 
     * @param mitarbeiter Der hinzuzufuegende Mitarbeiter
     * @return true, wenn der Mitarbeiter erfolgreich hinzugefuegt wurde
     */
    public boolean mitarbeiterHinzufuegen(Mitarbeiter mitarbeiter) {
        if (mitarbeiter == null) {
            return false;
        }
        return mitarbeiterListe.add(mitarbeiter);
    }

    /**
     * Entfernt einen Mitarbeiter aus der Verwaltung.
     * 
     * @param mitarbeiter Der zu entfernende Mitarbeiter
     * @return true, wenn der Mitarbeiter in der Liste war und entfernt wurde
     */
    public boolean mitarbeiterEntfernen(Mitarbeiter mitarbeiter) {
        return mitarbeiterListe.remove(mitarbeiter);
    }

    /**
     * Berechnet die Summe aller aktuellen Gehaelter aller Beschaeftigten.
     * 
     * @return Gesamte monatliche Gehaltssumme in Euro
     */
    public double berechneGesamtgehalt() {
        double summe = 0.0;
        for (Mitarbeiter m : mitarbeiterListe) {
            summe += m.getGehalt();
        }
        return summe;
    }

    /**
     * Berechnet das durchschnittliche Gehalt ueber alle Beschaeftigten.
     * 
     * @return Durchschnittsgehalt in Euro (0.0 wenn keine Mitarbeiter vorhanden sind)
     */
    public double berechneDurchschnittsgehalt() {
        if (mitarbeiterListe.isEmpty()) {
            return 0.0;
        }
        return berechneGesamtgehalt() / mitarbeiterListe.size();
    }

    /**
     * Sucht alle Mitarbeiter mit einem bestimmten Nachnamen (Gross-/Kleinschreibung ignoriert).
     * 
     * @param nachname Der gesuchte Nachname
     * @return Liste der gefundenen Mitarbeiter
     */
    public List<Mitarbeiter> sucheNachNachname(String nachname) {
        List<Mitarbeiter> treffer = new ArrayList<>();
        if (nachname == null) {
            return treffer;
        }
        for (Mitarbeiter m : mitarbeiterListe) {
            if (m.getNachname().equalsIgnoreCase(nachname.trim())) {
                treffer.add(m);
            }
        }
        return treffer;
    }

    /**
     * Filtert Mitarbeiter nach ihrer Kategorie (z. B. "Angestellter", "Auszubildender", "Aushilfe").
     * 
     * @param kategorie Name der Kategorie
     * @return Liste aller Mitarbeiter dieser Kategorie
     */
    public List<Mitarbeiter> filterNachKategorie(String kategorie) {
        List<Mitarbeiter> ergebnis = new ArrayList<>();
        if (kategorie == null) {
            return ergebnis;
        }
        for (Mitarbeiter m : mitarbeiterListe) {
            if (m.getKategorie().equalsIgnoreCase(kategorie.trim())) {
                ergebnis.add(m);
            }
        }
        return ergebnis;
    }

    /**
     * Fuehrt eine polymorphe Gehaltsanpassung fuer saemtliche Mitarbeiter durch.
     * Jede Kategorie wendet dabei ihre eigene Berechnungslogik an.
     * 
     * @param wert Anpassungswert fuer die Gehaltsanpassung
     */
    public void passeAlleGehaelterAn(double wert) {
        for (Mitarbeiter m : mitarbeiterListe) {
            m.passeGehaltAn(wert);
        }
    }
}
