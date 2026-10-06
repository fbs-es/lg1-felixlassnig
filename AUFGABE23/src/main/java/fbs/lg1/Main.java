package fbs.lg1;

/**
 * Zeigt beispielhaft den gesamten Ablauf des Online-Shop-Systems:
 * - Anlegen von Kunde, Lieferant und Produkten
 * - Erstellen einer Bestellung mit Positionen
 * - Berechnung des Gesamtwerts
 * - Stornierung mit Rückerstattung
 * - Prüfung von Nachschubbedarf und Auslösen einer Lieferantenbestellung
 */
public class Main {

    public static void main(String[] args) {
        // 1. Kunde anlegen
        Kunde felix = new Kunde(1, "Felix Lassnig", "felix@lassnig.at");
        System.out.println("Kunde angelegt: " + felix.getName() + " (" + felix.getEmail() + ")");

        // 2. Lieferant und Produkte anlegen
        Lieferant lieferant = new Lieferant(501, "TechSupply Austria GmbH");
        Produkt laptop = new Produkt(101, "Gaming Laptop", 1299.99, 4, 3, lieferant);
        Produkt maus = new Produkt(102, "Kabellose Maus", 29.90, 2, 5, lieferant);
        lieferant.addProdukt(laptop);
        lieferant.addProdukt(maus);

        System.out.println("Lieferant: " + lieferant.getName());
        System.out.println("Produkte im Sortiment: " + laptop.getBezeichnung() + ", " + maus.getBezeichnung());
        System.out.println();

        // 3. Bestellung für Felix erstellen
        Bestellung bestellung = new Bestellung(1001, felix);
        bestellung.addPosition(new Bestellposition(1, laptop, 1, 1299.99));
        bestellung.addPosition(new Bestellposition(2, maus, 2, 29.90));

        System.out.println("Bestellung #" + bestellung.getBestellnummer() + " erstellt für: " + felix.getName());
        System.out.println("Status: " + bestellung.getStatus());
        System.out.println("Anzahl Positionen: " + bestellung.getPosition().size());

        // 4. Gesamtwert der Bestellung berechnen
        double gesamtwert = bestellung.berechneGesamtwert();
        System.out.printf("Gesamtwert der Bestellung: %.2f EUR%n", gesamtwert);
        System.out.println();

        // 5. Nachschubbedarf prüfen und Lieferantenbestellung auslösen
        System.out.println("--- Nachschubprüfung ---");
        pruefeUndBestelleNachschub(laptop);
        pruefeUndBestelleNachschub(maus);
        System.out.println();

        // 6. Bestellung stornieren (inkl. automatischer Rückerstattung)
        System.out.println("--- Stornierung der Bestellung ---");
        boolean storniert = bestellung.stornieren();
        System.out.println("Stornierung erfolgreich? " + storniert);
        System.out.println("Neuer Status: " + bestellung.getStatus());
    }

    // Prüft, ob für ein Produkt Nachschub nötig ist, und löst möglicherweise eine
    // Lieferantenbestellung aus
    private static void pruefeUndBestelleNachschub(Produkt produkt) {
        if (produkt.hatNachschubbedarf()) {
            System.out.println("ACHTUNG: Mindestbestand bei '" + produkt.getBezeichnung()
                    + "' erreicht/unterschritten (Lager: " + produkt.getLagerbestand()
                    + ", Mindest: " + produkt.getMindestbestand() + ")!");

            // Lieferantenbestellung anstoßen
            int nachbestellMenge = 10;
            Lieferantenbestellung lb = new Lieferantenbestellung(9001, produkt, nachbestellMenge);
            System.out.println("-> Lieferantenbestellung #" + lb.getLieferantenbestellnummer()
                    + " über " + lb.getBestellteMenge() + " Stück ausgelöst bei "
                    + lb.getLieferant().getName() + ".");
        } else {
            System.out.println("Bestand für '" + produkt.getBezeichnung() + "' ist ausreichend (Lager: "
                    + produkt.getLagerbestand() + ").");
        }
    }
}
