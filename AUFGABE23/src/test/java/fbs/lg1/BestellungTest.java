package fbs.lg1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BestellungTest {

    // Test: Überprüft, ob die Bestellung initial den Status 'offen' hat
    @Test
    void testBestellungInitialerStatus() {
        Kunde kunde = new Kunde(1, "Felix Lassnig", "felix@lassnig.at");
        Bestellung bestellung = new Bestellung(201, kunde);

        assertThat(bestellung.getBestellnummer()).isEqualTo(201);
        assertThat(bestellung.getStatus()).isEqualTo(BestellungStatus.offen);
        assertThat(bestellung.getPosition()).isEmpty();
    }

    // Test: Überprüft die Berechnung des Gesamtwerts über alle enthaltenen Bestellpositionen
    @Test
    void testBerechneGesamtwert() {
        Kunde kunde = new Kunde(1, "Felix Lassnig", "felix@lassnig.at");
        Lieferant lieferant = new Lieferant(500, "Technik Handel GmbH");
        Produkt laptop = new Produkt(10, "Laptop", 800.0, 5, 2, lieferant);
        Produkt maus = new Produkt(11, "Maus", 25.0, 20, 5, lieferant);

        Bestellung bestellung = new Bestellung(202, kunde);
        bestellung.addPosition(new Bestellposition(1, laptop, 1, 800.0));
        bestellung.addPosition(new Bestellposition(2, maus, 2, 25.0));

        // Gesamtwert: (1 * 800.0) + (2 * 25.0) = 850.0 EUR
        assertThat(bestellung.berechneGesamtwert()).isEqualTo(850.0);
    }

    // Test: Überprüft, ob das Stornieren im Status 'offen' erfolgreich ist
    @Test
    void testStornierenErfolgreich() {
        Kunde kunde = new Kunde(1, "Felix Lassnig", "felix@lassnig.at");
        Bestellung bestellung = new Bestellung(203, kunde);

        boolean storniert = bestellung.stornieren();

        assertThat(storniert).isTrue();
        assertThat(bestellung.getStatus()).isEqualTo(BestellungStatus.storniert);
    }

    // Test: Überprüft, dass eine nicht mehr offene Bestellung nicht storniert werden darf
    @Test
    void testStornierenNichtErlaubtWennBereitsStorniert() {
        Kunde kunde = new Kunde(1, "Felix Lassnig", "felix@lassnig.at");
        Bestellung bestellung = new Bestellung(204, kunde);

        bestellung.stornieren();

        // Zweiter Stornierungsversuch muss abgewiesen werden
        boolean erneutStorniert = bestellung.stornieren();
        assertThat(erneutStorniert).isFalse();
    }
}
