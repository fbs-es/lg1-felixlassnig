package fbs.lg1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Tests für die Klasse Hersteller.
 */
class HerstellerTest {

    // Test: Gültigen Hersteller anlegen und Getter prüfen
    @Test
    void testHerstellerErstellung() {
        Hersteller hersteller = new Hersteller("Samsung", "hilfe@samsung.at");

        assertEquals("Samsung", hersteller.getHerstellername());
        assertEquals("hilfe@samsung.at", hersteller.getSupportEmail());
    }

    // Test: Ungültiger Herstellername (leer oder null)
    @Test
    void testHerstellernameUngueltig() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hersteller(null, "kontakt@firma.at");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Hersteller("  ", "kontakt@firma.at");
        });
    }

    // Test: Ungültige Support-E-Mail (ohne @, leer oder null)
    @Test
    void testSupportEmailUngueltig() {
        // E-Mail ohne @
        assertThrows(IllegalArgumentException.class, () -> {
            new Hersteller("Samsung", "support-samsung.at");
        });

        // E-Mail null
        assertThrows(IllegalArgumentException.class, () -> {
            new Hersteller("Samsung", null);
        });

        // E-Mail leer
        assertThrows(IllegalArgumentException.class, () -> {
            new Hersteller("Samsung", "   ");
        });
    }
}
