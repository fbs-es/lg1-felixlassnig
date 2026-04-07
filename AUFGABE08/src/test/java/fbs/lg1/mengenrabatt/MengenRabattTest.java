package fbs.lg1.mengenrabatt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MengenRabattTest {
    // Test: Überprüft, dass valueOf() den korrekten Enum-Wert zurückgibt
    @Test
    void testValueOf() {
        assertEquals(MengenRabatt.Stufe.Medium, MengenRabatt.Stufe.valueOf("Medium"));
    }

    // Test: Überprüft, dass genau 4 Rabattstufen vorhanden sind
    @Test
    void testValues() {
        assertEquals(4, MengenRabatt.Stufe.values().length);
    }

    // Test: Überprüft, dass alle erwarteten Rabattstufen existieren
    @Test
    void testAllStufenExist() {
        assertEquals(MengenRabatt.Stufe.Mini, MengenRabatt.Stufe.valueOf("Mini"));
        assertEquals(MengenRabatt.Stufe.Small, MengenRabatt.Stufe.valueOf("Small"));
        assertEquals(MengenRabatt.Stufe.Medium, MengenRabatt.Stufe.valueOf("Medium"));
        assertEquals(MengenRabatt.Stufe.Large, MengenRabatt.Stufe.valueOf("Large"));
    }

    // Test: Überprüft, dass valueOf() mit ungültigem String eine Exception wirft
    @Test
    void testValueOfInvalid() {
        try {
            MengenRabatt.Stufe.valueOf("Ungueltig");
            throw new AssertionError("Exception erwartet");
        } catch (IllegalArgumentException e) {
            // Erwartet: Exception wird geworfen
        }
    }

}
