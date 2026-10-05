package fbs.lg1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests für die Klasse Kunde.
 */
class KundeTest {

    // Test: Gültigen Kunden erstellen und Getter prüfen
    @Test
    void testKundeErstellung() {
        Kunde kunde = new Kunde("K-123", "Felix Lassnig");

        assertEquals("K-123", kunde.getKundennummer());
        assertEquals("Felix Lassnig", kunde.getName());
    }

    // Test: Ungültige Kundennummer (leer oder null)
    @Test
    void testKundennummerUngueltig() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Kunde(null, "Felix Lassnig");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Kunde("   ", "Felix Lassnig");
        });
    }

    // Test: Ungültiger Name (leer oder null)
    @Test
    void testNameUngueltig() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Kunde("K-123", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Kunde("K-123", "");
        });
    }
}
