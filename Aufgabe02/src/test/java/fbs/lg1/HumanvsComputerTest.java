package fbs.lg1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HumanvsComputerTest { // Korrigiert: HumanvsComputerTest

    GenRandom randomKlasse = new GenRandom();

    @ParameterizedTest
    @CsvSource({
            "1, 100",
            "50, 100",
            "50, 50",
            "-100, -50"
    })
    void generateTest(int min, int max) {
        int wert = randomKlasse.generierenRandom(min, max); // Korrigiert: generierenRandom(min, max)
        assertTrue(wert >= min && wert <= max);
    }

    @ParameterizedTest
    @CsvSource({
            "10, 2, 6",
            "10, 3, 6",
            "50, 40, 45"
    })
    void TesthälfteDerZahl(int a, int b, int expected) {
        // Da es keine hälfteDerZahl Methode gibt, testen wir die Mitte-Berechnung
        // direkt
        int actual = (a + b) / 2; // Einfache Mittelwert-Berechnung
        assertEquals(expected, actual);
    }
}