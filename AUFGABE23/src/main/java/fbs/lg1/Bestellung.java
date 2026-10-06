package fbs.lg1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bestellung {
    private int bestellnummer;
    private LocalDate bestelldatum;
    private BestellungStatus status;
    private Kunde kunde;
    private List<Bestellposition> positionen;

    public Bestellung(int bestellnummer, Kunde kunde) {
        this.bestellnummer = bestellnummer;
        this.bestelldatum = LocalDate.now();
        this.status = BestellungStatus.offen;
        this.kunde = kunde;
        kunde.addBestellung(this);
        this.positionen = new ArrayList<>();
    }

    // Methoden

    // Bestellposition hinzufügen
    public void addPosition(Bestellposition position) {
        this.positionen.add(position);
    }

    // Gesamtwert berechnen
    public double berechneGesamtwert() {
        double gesamt = 0.0;
        for (Bestellposition pos : positionen) {
            gesamt += pos.getMenge() * pos.getEinzelpreis();
        }
        return gesamt;
    }

    public boolean stornieren() {
        if (this.status != BestellungStatus.offen) {
            return false;
        }
        rueckerstattung();

        this.status = BestellungStatus.storniert;
        return true;
    }

    private void rueckerstattung() {
        double erstattungsBetrag = berechneGesamtwert();

        System.out.println(
                "Rückerstattung von " + erstattungsBetrag + " EUR an Kunde " + kunde.getName() + " veranlasst.");
    }

    // Getter

    public int getBestellnummer() {
        return bestellnummer;
    }

    public BestellungStatus getStatus() {
        return status;
    }

    public List<Bestellposition> getPosition() {
        return positionen;
    }

}