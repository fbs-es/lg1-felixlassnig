package fbs.lg1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ProduktTest {

    // Test: Überprüft, ob ein Produkt korrekt angelegt wird
    @Test
    void testProduktErstellung() {
        Lieferant lieferant = new Lieferant(501, "Hardware Vertrieb");
        Produkt produkt = new Produkt(301, "Tastatur", 49.99, 15, 5, lieferant);

        assertThat(produkt.getProduktnummer()).isEqualTo(301);
        assertThat(produkt.getBezeichnung()).isEqualTo("Tastatur");
        assertThat(produkt.getEinzelpreis()).isEqualTo(49.99);
        assertThat(produkt.getLagerbestand()).isEqualTo(15);
        assertThat(produkt.getMindestbestand()).isEqualTo(5);
        assertThat(produkt.getLieferant()).isEqualTo(lieferant);
    }

    // Test: Überprüft, ob Nachschubbedarf bei Erreichen oder Unterschreiten des Mindestbestands erkannt wird
    @Test
    void testNachschubbedarfErkennung() {
        Lieferant lieferant = new Lieferant(501, "Hardware Vertrieb");
        // Lagerbestand (5) == Mindestbestand (5) -> Nachschub erforderlich
        Produkt produkt = new Produkt(302, "USB-Hub", 19.99, 5, 5, lieferant);

        assertThat(produkt.hatNachschubbedarf()).isTrue();

        // Lagerbestand fällt unter den Mindestbestand -> weiterhin Nachschub erforderlich
        produkt.setLagerbestand(3);
        assertThat(produkt.hatNachschubbedarf()).isTrue();

        // Lagerbestand wird auf 20 aufgefüllt -> kein Nachschubbedarf mehr
        produkt.setLagerbestand(20);
        assertThat(produkt.hatNachschubbedarf()).isFalse();
    }
}
