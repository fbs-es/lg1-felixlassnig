package fbs.lg1;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests für die Klasse Bewertung.
 */
class BewertungTest {

    private Kunde kunde;
    private Produkt produkt;

    @BeforeEach
    void setUp() {
        Hersteller hersteller = new Hersteller("Sony", "support@sony.at");
        produkt = new Produkt("P-100", "PlayStation 5", new BigDecimal("499.99"), hersteller);
        kunde = new Kunde("K-001", "Felix Lassnig");
    }

    // Test: Gültige Bewertung erstellen und Verknüpfung zum Produkt prüfen
    @Test
    void testBewertungErstellung() {
        LocalDate heute = LocalDate.now();
        Bewertung bewertung = new Bewertung(kunde, produkt, 5, "Super Konsole!", heute, true);

        assertEquals(kunde, bewertung.getKunde());
        assertEquals(produkt, bewertung.getProdukt());
        assertEquals(5, bewertung.getSterne());
        assertEquals("Super Konsole!", bewertung.getKommentar());
        assertEquals(heute, bewertung.getErstellungsdatum());
        assertTrue(bewertung.hatGekauft());
        // Das Produkt muss die Bewertung automatisch enthalten
        assertTrue(produkt.getBewertungen().contains(bewertung));
    }

    // Test: Nicht gekauft Flag
    @Test
    void testNichtGekauft() {
        Bewertung bewertung = new Bewertung(kunde, produkt, 3, "Nur getestet", LocalDate.now(), false);
        assertFalse(bewertung.hatGekauft());
    }

    // Test: Sterne müssen zwischen 1 und 5 liegen
    @Test
    void testSterneGrenzen() {
        // 0 Sterne ist ungültig
        assertThrows(IllegalArgumentException.class, () -> {
            new Bewertung(kunde, produkt, 0, "Zu schlecht", LocalDate.now(), true);
        });

        // 6 Sterne ist ungültig
        assertThrows(IllegalArgumentException.class, () -> {
            new Bewertung(kunde, produkt, 6, "Zu gut", LocalDate.now(), true);
        });
    }

    // Test: Maximal eine Antwort pro Bewertung erlaubt
    @Test
    void testMaximalEineAntwort() {
        Bewertung bewertung = new Bewertung(kunde, produkt, 4, "Guter Controller", LocalDate.now(), true);
        Antwort antwort1 = new Antwort("Vielen Dank!", LocalDate.now());
        Antwort antwort2 = new Antwort("Zweite Antwort", LocalDate.now());

        // Erste Antwort erfolgreich setzen
        bewertung.setAntwort(antwort1);
        assertEquals(antwort1, bewertung.getAntwort());

        // Zweite Antwort muss fehlschlagen
        assertThrows(IllegalArgumentException.class, () -> {
            bewertung.setAntwort(antwort2);
        });
    }

    // Test: Pflichtangaben dürfen nicht null sein
    @Test
    void testPflichtangabenNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bewertung(null, produkt, 4, "Kein Kunde", LocalDate.now(), true);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Bewertung(kunde, null, 4, "Kein Produkt", LocalDate.now(), true);
        });
    }
}
