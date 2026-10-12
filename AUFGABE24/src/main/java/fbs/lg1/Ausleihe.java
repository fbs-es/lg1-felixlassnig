package fbs.lg1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Ausleihe {
    private Buch buch;
    private Nutzer nutzer;
    private LocalDate ausleihdatum;
    private Ausleihstatus status;
    private boolean verlaengert;
    private double verspaetungsStrafe;
    private LocalDate geplantesRueckgabedatum;

    // Konstruktor
    public Ausleihe(Buch buch, Nutzer nutzer, LocalDate ausleihdatum) {
        this.buch = buch;
        this.nutzer = nutzer;
        this.ausleihdatum = ausleihdatum;
        this.geplantesRueckgabedatum = ausleihdatum.plusDays(14);
        this.status = Ausleihstatus.AKTIV;
        this.verlaengert = false;
        this.buch.setVerfuegbar(false);
    }

    // Getter

    public Buch getBuch() {
        return buch;
    }

    public Nutzer getNutzer() {
        return nutzer;
    }

    public LocalDate getAusleihdatum() {
        return ausleihdatum;
    }

    public LocalDate getGeplantesRueckgabedatum() {
        return geplantesRueckgabedatum;
    }

    public Ausleihstatus getStatus() {
        return status;
    }

    public boolean isVerlaengert() {
        return verlaengert;
    }

    public double getVerspaetungsStrafe() {
        return verspaetungsStrafe;
    }

    public boolean verlaengern() {

        if (verlaengert) {
            System.out.println("Deine Ausleihe kann nicht mehr verlängert werden.");

            return false;
        }

        System.out.println("Buch wurde noch nicht überzogen");
        this.geplantesRueckgabedatum = this.geplantesRueckgabedatum.plusDays(14);
        this.verlaengert = true;
        return true;

    }

    public void pruefeUeberzogen(LocalDate aktuellesDatum) {
        if (this.status == Ausleihstatus.AKTIV && aktuellesDatum.isAfter(this.geplantesRueckgabedatum)) {
            this.status = Ausleihstatus.UEBERZOGEN;
        }
    }

    public void rueckgabe(LocalDate rueckgabedatum) {
        if (rueckgabedatum.isAfter(this.geplantesRueckgabedatum)) {
            long tage = ChronoUnit.DAYS.between(this.geplantesRueckgabedatum, rueckgabedatum);
            this.verspaetungsStrafe = tage * 1.0;
        }

        this.status = Ausleihstatus.ZURUECKGEGEBEN;
        this.buch.setVerfuegbar(true);
    }

}
