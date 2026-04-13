package fbs.lg1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KinoVerwaltungTest {

    private KinoVerwaltung kino;

    @BeforeEach
    void setUp() {
        kino = new KinoVerwaltung();
    }

    @Test
    void testInit() {
        KinoVerwaltung aufgabe = new KinoVerwaltung(3, 3);
        assertArrayEquals(new char[][] { { 'F', 'F', 'F' }, { 'F', 'F', 'F' }, { 'R', 'R', 'R' } },
                aufgabe.getKinosaal());
    }

    @Test
    void testInitialiesierungAboReihe() {
        char[][] saal = kino.getKinosaal();
        int letzteReihe = saal.length - 1;

        for (int j = 0; j < saal[letzteReihe].length; j++) {
            assertEquals('R', saal[letzteReihe][j], "Sitzplatz sollte muss reserviert sein.");
        }
    }

    @Test
    void testSucheFreiePlaetze() {
        int[][] vorschlag = kino.sucheFreiePlaetze(3);

        assertNotNull(vorschlag, "Es sollte ein Vorschlag gefunden werden.");
        assertEquals(3, vorschlag.length, "Der Vorschlag muss 3 Koordinaten enthalten.");

        for (int[] pos : vorschlag) {
            assertEquals('V', kino.getKinosaal()[pos[0]][pos[1]], "Platz muss V sein.");
        }
    }

    @Test
    void testVorschlagAnnehmen() {
        int[][] vorschlag = kino.sucheFreiePlaetze(2);
        kino.vorschlagAnnehmen(vorschlag);

        for (int[] pos : vorschlag) {
            assertEquals('R', kino.getKinosaal()[pos[0]][pos[1]], "Platz muss nun R sein.");
        }
    }

    @Test
    void testVorschlagAblehnen() {
        int[][] vorschlag = kino.sucheFreiePlaetze(2);
        kino.vorschlagAblehnen(vorschlag);

        for (int[] pos : vorschlag) {
            assertEquals('F', kino.getKinosaal()[pos[0]][pos[1]], "Platz muss wieder F sein.");
        }
    }
}
