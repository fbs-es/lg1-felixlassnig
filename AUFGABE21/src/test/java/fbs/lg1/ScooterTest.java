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
        // Mindestens 10€ erforderlich
        assertThrows(IllegalArgumentException.class, () -> new Kunde(1, "Anna", "anna@test.de", 9.99));

        Kunde kunde = new Kunde(1, "Anna", "anna@test.de", 10.00);
        assertEquals(10.00, kunde.getBalance());
        assertFalse(kunde.isAccountLocked());
        assertEquals(0, kunde.getMahnungsStufe());
    }

}
