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

    // Konstruktor
    public Bibliothek(String name, String adresse) {
        this.name = name;
        this.adresse = adresse;
        this.buecher = new ArrayList<>();
        this.ausleihen = new ArrayList<>();
        this.nutzer = new ArrayList<>();
    }

    // Getter

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Buch> getBuecher() {
        return buecher;
    }

    public List<Ausleihe> getAusleihen() {
        return ausleihen;
    }

    public List<Nutzer> getNutzer() {
        return nutzer;
    }

    // Methoden

    public void neuesBuchHinzufuegen(Buch buch) {
        this.buecher.add(buch);
    }

    public void nutzerRegistrieren(Nutzer nutzer) {
        this.nutzer.add(nutzer);
    }

    public Ausleihe buchAusleihen(Buch buch, Nutzer nutzer, LocalDate ausleihdatum) {
        if (!buch.isVerfuegbar()) {
            return null;
        }
        Ausleihe neueAusleihe = new Ausleihe(buch, nutzer, ausleihdatum);
        this.ausleihen.add(neueAusleihe);
        return neueAusleihe;

    }
}
