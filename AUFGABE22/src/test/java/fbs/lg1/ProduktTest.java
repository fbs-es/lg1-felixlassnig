package fbs.lg1;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests für die Klasse Produkt.
 */
class ProduktTest {

    private Hersteller hersteller;
    private Produkt produkt;
    private Kunde kunde;

    @BeforeEach
    void setUp() {
        hersteller = new Hersteller("Logitech", "support@logitech.at");
        produkt = new Produkt("P-001", "MX Master 3", new BigDecimal("99.99"), hersteller);
        kunde = new Kunde("K-001", "Felix Lassnig");
    }

    // Test: Gültige Produkterstellung und Getter
    @Test
    void testProduktErstellung() {
        assertEquals("P-001", produkt.getProduktNummer());
        assertEquals("MX Master 3", produkt.getProduktName());
        assertEquals(new BigDecimal("99.99"), produkt.getProduktPreis());
        assertEquals(hersteller, produkt.getHersteller());
        assertTrue(produkt.getBewertungen().isEmpty());
    }

    // Test: Durchschnittliche Sterneberechnung
    @Test
    void testBerechneDurchschnittssterne() {
        // Noch keine Bewertungen -> 0.0
        assertEquals(0.0, produkt.berechneDurchschnittssterne());

        // Zwei Bewertungen anlegen (5 Sterne und 3 Sterne)
        new Bewertung(kunde, produkt, 5, "Top Maus!", LocalDate.now(), true);
        new Bewertung(kunde, produkt, 3, "Ganz ok", LocalDate.now(), true);

        // Durchschnitt (5 + 3) / 2 = 4.0
        assertEquals(4.0, produkt.berechneDurchschnittssterne());
    }

    // Test: Ungültige Eingaben im Konstruktor
    @Test
    void testUngueltigeEingaben() {
        // Leere Produktnummer
        assertThrows(IllegalArgumentException.class, () -> {
            new Produkt("", "Tastatur", new BigDecimal("49.99"), hersteller);
        });

        // Leerer Name
        assertThrows(IllegalArgumentException.class, () -> {
            new Produkt("P-002", "   ", new BigDecimal("49.99"), hersteller);
        });

        // Negativer Preis
        assertThrows(IllegalArgumentException.class, () -> {
            new Produkt("P-002", "Tastatur", new BigDecimal("-5.00"), hersteller);
        });

        // Preis 0
        assertThrows(IllegalArgumentException.class, () -> {
            new Produkt("P-002", "Tastatur", BigDecimal.ZERO, hersteller);
        });

        // Hersteller fehlt
        assertThrows(IllegalArgumentException.class, () -> {
            new Produkt("P-002", "Tastatur", new BigDecimal("49.99"), null);
        });
    }

    // Test: Bewertung gehört zu einem anderen Produkt
    @Test
    void testBewertungGehoertZuAnderemProdukt() {
        Produkt anderesProdukt = new Produkt("P-002", "Tastatur", new BigDecimal("49.99"), hersteller);
        Bewertung bewertung = new Bewertung(kunde, anderesProdukt, 4, "Gute Tastatur", LocalDate.now(), true);

        assertThrows(IllegalArgumentException.class, () -> {
            produkt.addBewertung(bewertung);
        });
    }
}
