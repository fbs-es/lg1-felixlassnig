package fbs.lg1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class KundeTest {

    // Test: Überprüft, ob ein Kunde korrekt mit allen Attributen angelegt wird
    @Test
    void testKundeErstellung() {
        Kunde kunde = new Kunde(1, "Felix Lassnig", "felix@lassnig.at");

        assertThat(kunde.getKundennummer()).isEqualTo(1);
        assertThat(kunde.getName()).isEqualTo("Felix Lassnig");
        assertThat(kunde.getEmail()).isEqualTo("felix@lassnig.at");
        assertThat(kunde.getBestellungen()).isEmpty();
    }

    // Test: Überprüft, ob eine Bestellung zur Bestellliste des Kunden hinzugefügt werden kann
    @Test
    void testAddBestellung() {
        Kunde kunde = new Kunde(1, "Felix Lassnig", "felix@lassnig.at");
        Bestellung bestellung = new Bestellung(101, kunde);

        assertThat(kunde.getBestellungen()).hasSize(1);
        assertThat(kunde.getBestellungen()).contains(bestellung);
    }
}
