package fbs.lg1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class KinoVerwaltungTest {
    @Test
    void testInit() {
        KinoVerwaltung aufgabe = new KinoVerwaltung(3, 3);
        assertArrayEquals(new char[][] { { 'F', 'F', 'F' }, { 'F', 'F', 'F' }, { 'R', 'R', 'R' } },
                aufgabe.getKinosaal());
    }
}
