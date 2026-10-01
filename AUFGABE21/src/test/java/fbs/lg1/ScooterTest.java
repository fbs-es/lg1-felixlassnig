package fbs.lg1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScooterTest {

    @Test
    void testScooterInitialisierung() {
        Scooter scooter = new Scooter("SC-001");
        assertEquals("SC-001", scooter.getScooterId());
        assertEquals(100, scooter.getBattery());
        assertTrue(scooter.isLocked());
    }

    @Test
    void testKundeRegistrierungMindestguthaben() {
        // Unter 10€ -> Fehler erwartet
        assertThrows(IllegalArgumentException.class, () -> new Kunde(1, "Anna", "anna@test.de", 9.99));

        // Ab 10€ -> erfolgreich
        Kunde kunde = new Kunde(1, "Anna", "anna@test.at", 10.00);
        assertEquals(10.00, kunde.getBalance());
        assertFalse(kunde.isAccountLocked());
    }

    @Test
    void testRollerAusleihenErfolgreich() {
        Kunde kunde = new Kunde(1, "Felix", "felix@test.at", 15.0);
        Scooter scooter = new Scooter("SC-001");

        boolean erfolg = kunde.rollerAusleihen(scooter);

        assertTrue(erfolg);
        assertFalse(scooter.isLocked());
        assertEquals(scooter, kunde.getRentedScooter());
    }

    @Test
    void testRollerAusleihenAkkuZuSchwach() {
        Kunde kunde = new Kunde(1, "Felix", "felix@test.at", 15.0);
        Scooter scooter = new Scooter("SC-001", 15, true); // Akku <= 15% darf nicht ausgeliehen werden

        boolean erfolg = kunde.rollerAusleihen(scooter);

        assertFalse(erfolg);
        assertTrue(scooter.isLocked());
    }

    @Test
    void testFahrtBeenden() {
        Kunde kunde = new Kunde(1, "Felix", "felix@test.at", 15.0);
        Scooter scooter = new Scooter("SC-001");
        kunde.rollerAusleihen(scooter);

        // 10 Minuten Fahrt: 10 * 0.20€ = 2.00€ Kosten, 10% Akkuverlust
        boolean erfolg = kunde.fahrtBeenden(10);

        assertTrue(erfolg);
        assertEquals(13.0, kunde.getBalance(), 0.01);
        assertEquals(90, scooter.getBattery());
        assertTrue(scooter.isLocked());
    }

    @Test
    void testKontoAusgleichen() {
        Kunde kunde = new Kunde(1, "Felix", "felix@test.at", 10.0);
        kunde.kontoAusgleichen(15.0);
        assertEquals(25.0, kunde.getBalance(), 0.01);
    }

    @Test
    void testFahrtMitEchtenZeitstempelnUndHistorie() {
        Kunde kunde = new Kunde(2, "Lisa", "lisa@test.at", 20.0);
        Scooter scooter = new Scooter("SC-002");

        java.time.LocalDateTime start = java.time.LocalDateTime.of(2026, 9, 29, 14, 0, 0);
        java.time.LocalDateTime ende = java.time.LocalDateTime.of(2026, 9, 29, 14, 15, 0);

        assertTrue(kunde.rollerAusleihen(scooter, start));
        assertFalse(scooter.isLocked());

        // 15 Minuten Fahrt: 15 * 0.20€ = 3.00€ Kosten
        assertTrue(kunde.fahrtBeenden(ende));
        assertTrue(scooter.isLocked());
        assertEquals(17.0, kunde.getBalance(), 0.01);
        assertEquals(85, scooter.getBattery());

        // Historie überprüfen
        assertEquals(1, kunde.getFahrtHistorie().size());
        Fahrt fahrt = kunde.getFahrtHistorie().get(0);
        assertEquals(scooter, fahrt.getScooter());
        assertEquals(start, fahrt.getStartZeit());
        assertEquals(ende, fahrt.getEndZeit());
        assertEquals(15, fahrt.getDauerInMinuten());
        assertEquals(3.00, fahrt.getKosten(), 0.01);
    }
}
