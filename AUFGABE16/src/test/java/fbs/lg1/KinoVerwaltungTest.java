package fbs.lg1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KinoVerwaltungTest {

    private Kinosaal saal;
    private KinoService service;

    @BeforeEach
    void setUp() {
        saal = new Kinosaal(13, 9);
        service = new KinoService(saal);
    }

    @Test
    void testKinosaalDimensionen() {
        assertEquals(13, saal.getAnzahlReihen(), "Reihenanzahl falsch.");
        assertEquals(9, saal.getAnzahlPlaetze(), "Platzanzahl falsch.");
    }

    @Test
    void testGetSetStatus() {
        saal.setStatus(5, 5, Kinosaal.RESERVIERT);
        assertEquals(Kinosaal.RESERVIERT, saal.getStatus(5, 5), "Status wurde nicht korrekt gesetzt/gelesen.");
    }

    @Test
    void testAboReiheInitialisierung() {
        for (int j = 0; j < 9; j++) {
            assertEquals(Kinosaal.RESERVIERT, saal.getStatus(12, j), "Abo-Reihe muss R sein.");
        }
        assertEquals(Kinosaal.FREI, saal.getStatus(0, 0), "Normale Reihe muss F sein.");
    }

    @Test
    void testSucheFreiePlaetzeErfolg() {
        int[][] vorschlag = service.sucheFreiePlaetze(3);
        assertNotNull(vorschlag);
        assertEquals(3, vorschlag.length);
        assertEquals(Kinosaal.VORGEMERKT, saal.getStatus(vorschlag[0][0], vorschlag[0][1]));
    }

    @Test
    void testSucheFreiePlaetzeUngueltigeAnzahl() {
        int[][] vorschlag = service.sucheFreiePlaetze(5);
        assertEquals(0, vorschlag.length, "Bei ungültiger Anzahl sollte leeres Array kommen.");
    }

    @Test
    void testVorschlagAnnehmen() {
        int[][] vorschlag = service.sucheFreiePlaetze(2);
        service.vorschlagAnnehmen(vorschlag);

        for (int[] pos : vorschlag) {
            assertEquals(Kinosaal.RESERVIERT, saal.getStatus(pos[0], pos[1]), "Status muss nun R sein.");
        }
    }

    @Test
    void testVorschlagAblehnen() {
        int[][] vorschlag = service.sucheFreiePlaetze(2);
        service.vorschlagAblehnen(vorschlag);

        for (int[] pos : vorschlag) {
            assertEquals(Kinosaal.FREI, saal.getStatus(pos[0], pos[1]), "Status muss wieder F sein.");
        }
    }

    @Test
    void testStatistikDatenKorrekt() {
        saal.setStatus(0, 0, Kinosaal.RESERVIERT);

        Map<Character, Integer> stats = service.getStatistikDaten();

        assertEquals(10, stats.get(Kinosaal.RESERVIERT),
                "Die Anzahl der reservierten Plätze in der Statistik stimmt nicht.");
        assertTrue(stats.containsKey(Kinosaal.FREI));
        assertTrue(stats.containsKey(Kinosaal.VORGEMERKT));
    }

    @Test
    void testKompletterBuchungsablauf() {
        int[][] vorschlag = service.sucheFreiePlaetze(1);
        int r = vorschlag[0][0];
        int s = vorschlag[0][1];

        assertEquals(Kinosaal.VORGEMERKT, saal.getStatus(r, s));
        service.vorschlagAnnehmen(vorschlag);

        assertEquals(Kinosaal.RESERVIERT, saal.getStatus(r, s));
    }
}