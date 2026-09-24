package fbs.lg1;

import java.math.BigDecimal;

public class Sparkonto {
    private String kontoInhaber;
    private BigDecimal kontoStand;
    private String kontoNummer;
    private String iban;

    public Sparkonto(String kontoInhaber, BigDecimal startguthaben, String iban, String kontoNummer) { 
        setKontoInhaber(kontoInhaber);

        if (iban == null || iban.isBlank()) {
            throw new IllegalArgumentException("IBAN darf nicht leer sein.");
        }
        this.iban = iban.trim();

        if (kontoNummer == null || kontoNummer.isBlank()) {
            throw new IllegalArgumentException("Kontonummer darf nicht leer sein.");
        }
        this.kontoNummer = kontoNummer.trim();

        if (startguthaben == null) {
            throw new IllegalArgumentException("Startguthaben darf nicht null sein.");
        }

        if (startguthaben.compareTo(BigDecimal.ZERO) < 0) {
            this.kontoStand = BigDecimal.ZERO;
        } else {
            this.kontoStand = startguthaben;
        }
    }

    public void setKontoInhaber(String neuerInhaber) {
        if (neuerInhaber == null || neuerInhaber.isBlank()) {
            throw new IllegalArgumentException("Der Name darf nicht leer sein.");
        }
        this.kontoInhaber = neuerInhaber.trim();
    }

    public String getKontoNummer() {
        return kontoNummer;
    }

    public String getIban() {
        return iban;
    }

    public String getKontoInhaber() {
        return kontoInhaber;
    }

    public BigDecimal getKontoStand() {
        return kontoStand;
    }

    public void einzahlen(BigDecimal betrag) {
        if (betrag == null) {
            System.out.println("Einzahlungsbetrag darf nicht null sein.");
            return;
        }
        if (betrag.compareTo(BigDecimal.ZERO) > 0) {
            kontoStand = kontoStand.add(betrag);
        } else {
            System.out.println("Einzahlungsbetrag muss positiv sein.");
        }
    }

    public void auszahlen(BigDecimal betrag) {
        if (betrag == null) {
            System.out.println("Abhebungsbetrag darf nicht null sein.");
            return;
        }
        if (betrag.compareTo(BigDecimal.ZERO) > 0 && betrag.compareTo(kontoStand) <= 0) {
            kontoStand = kontoStand.subtract(betrag);
        } else if (betrag.compareTo(kontoStand) > 0) {
            System.out.println("Nicht genügend Guthaben für diese Abhebung.");
        } else {
            System.out.println("Abhebungsbetrag muss positiv sein.");
        }
    }

    public boolean transferAmount(Sparkonto zielKonto, BigDecimal betrag) {
        if (zielKonto == null) {
            System.out.println("Transfer fehlgeschlagen: Zielkonto existiert nicht.");
            return false;
        }
        if (betrag == null) {
            System.out.println("Transfer fehlgeschlagen: Überweisungsbetrag darf nicht null sein.");
            return false;
        }
        if (betrag.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Transfer fehlgeschlagen: Überweisungsbetrag muss positiv sein.");
            return false;
        }
        if (betrag.compareTo(kontoStand) > 0) {
            System.out.println("Transfer fehlgeschlagen: Nicht genügend Guthaben auf dem Hauptkonto.");
            return false;
        }

        kontoStand = kontoStand.subtract(betrag);
        zielKonto.kontoStand = zielKonto.kontoStand.add(betrag);
        return true;
    }

    public boolean ueberweisen(Sparkonto zielKonto, BigDecimal betrag) {
        return transferAmount(zielKonto, betrag);
    }
}
