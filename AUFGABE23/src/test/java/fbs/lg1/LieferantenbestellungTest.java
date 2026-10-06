package fbs.lg1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LieferantenbestellungTest {

    // Test: Überprüft, ob die Lieferantenbestellung korrekt aufgebaut wird und der Lieferant vom Produkt übernommen wird
    @Test
    void testLieferantenbestellungErstellung() {
        Lieferant lieferant = new Lieferant(502, "IT Distributor");
        Produkt monitor = new Produkt(401, "Monitor 27 Zoll", 249.00, 2, 5, lieferant);

        Lieferantenbestellung lb = new Lieferantenbestellung(8001, monitor, 15);

        assertThat(lb.getLieferantenbestellnummer()).isEqualTo(8001);
        assertThat(lb.getProdukt()).isEqualTo(monitor);
        // Lieferant muss automatisch dem Lieferanten des Produkts entsprechen
        assertThat(lb.getLieferant()).isEqualTo(lieferant);
        assertThat(lb.getBestellteMenge()).isEqualTo(15);
        assertThat(lb.getBestelldatum()).isNotNull();
    }
}
