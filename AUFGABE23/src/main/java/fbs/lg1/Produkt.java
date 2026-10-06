package fbs.lg1;

public class Produkt {
    // Attribute eines Produkts
    private int produktnummer;
    private String bezeichnung;
    private double einzelpreis;
    private int lagerbestand;
    private int mindestbestand;
    private Lieferant lieferant;

    // Konstruktor zur Initialisierung eines Produkts
    public Produkt(int produktnummer, String bezeichnung, double einzelpreis, int lagerbestand, int mindestbestand,
            Lieferant lieferant)

    {
        this.produktnummer = produktnummer;
        this.bezeichnung = bezeichnung;
        this.einzelpreis = einzelpreis;
        this.lagerbestand = lagerbestand;
        this.mindestbestand = mindestbestand;
        this.lieferant = lieferant;
    }

    // Prüft ob der Mindestbestand erreicht oder unterschritten wurde
    public boolean hatNachschubbedarf() {
        return this.lagerbestand <= this.mindestbestand;
    }

    // Getter
    public int getProduktnummer() {
        return produktnummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public double getEinzelpreis() {
        return einzelpreis;
    }

    public int getLagerbestand() {
        return lagerbestand;
    }

    public int getMindestbestand() {
        return mindestbestand;
    }

    public Lieferant getLieferant() {
        return lieferant;
    }

    // Setter für Werte die sich im laufenden Betrieb ändern können
    public void setEinzelpreis(double einzelpreis) {
        this.einzelpreis = einzelpreis;
    }

    public void setLagerbestand(int lagerbestand) {
        this.lagerbestand = lagerbestand;
    }
}
