package fbs.lg1;

public class Bestellposition {
    private int positionsnummer;
    private int menge;
    private double einzelpreis;
    private Produkt produkt;

    // Konstruktor
    public Bestellposition(int positionsnummer, Produkt produkt, int menge, double einzelpreis) {
        this.positionsnummer = positionsnummer;
        this.produkt = produkt;
        this.menge = menge;
        this.einzelpreis = einzelpreis;
    }

    // Getter
    public int getPositionsnummer() {
        return positionsnummer;
    }

    public int getMenge() {
        return menge;
    }

    public double getEinzelpreis() {
        return einzelpreis;
    }

    public Produkt getProdukt() {
        return produkt;
    }
}
