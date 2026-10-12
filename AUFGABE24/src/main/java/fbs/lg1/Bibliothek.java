package fbs.lg1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bibliothek {
    // Attribute
    private String name;
    private String adresse;
    private List<Buch> buecher;
    private List<Nutzer> nutzer;
    private List<Ausleihe> ausleihen;

    // Konstruktor von Bibliothek
    public Bibliothek(String name, String adresse) {
        this.name = name;
        this.adresse = adresse;
        this.buecher = new ArrayList<>();
        this.ausleihen = new ArrayList<>();
        this.nutzer = new ArrayList<>();
    }

    // Getter
    // Hier wird der Name geholt
    public String getName() {
        return name;
    }

    // Hier wird die Adresse geholt
    public String getAdresse() {
        return adresse;
    }

    // Hier wird die Liste von Büchern geholt
    public List<Buch> getBuecher() {
        return buecher;
    }

    // Hier wird die Liste von Ausgeliehenen Büchern geholt
    public List<Ausleihe> getAusleihen() {
        return ausleihen;
    }

    // Hier wird die Liste von Nutzern geholt
    public List<Nutzer> getNutzer() {
        return nutzer;
    }

    // Methoden

    // Hier werden neue Bücher hinzugefügt
    public void neuesBuchHinzufuegen(Buch buch) {
        this.buecher.add(buch);
    }

    // Hier werden Nutzer registriert
    public void nutzerRegistrieren(Nutzer nutzer) {
        this.nutzer.add(nutzer);
    }

    // Hier wird die Funktion zum Buch ausleihen erstellt
    public Ausleihe buchAusleihen(Buch buch, Nutzer nutzer, LocalDate ausleihdatum) {
        if (!buch.isVerfuegbar()) {
            return null;
        }
        Ausleihe neueAusleihe = new Ausleihe(buch, nutzer, ausleihdatum);
        this.ausleihen.add(neueAusleihe);
        return neueAusleihe;

    }
}
