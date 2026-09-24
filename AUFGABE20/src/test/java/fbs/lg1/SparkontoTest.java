package fbs.lg1;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SparkontoTest {

    @Test
    void testKontoErstellen() {
        Sparkonto konto = new Sparkonto("Max Mustermann", new BigDecimal("1000.00"), "AT123456789012345678", "12345678");
        assertEquals("Max Mustermann", konto.getKontoInhaber());
        assertEquals(new BigDecimal("1000.00"), konto.getKontoStand());
        assertEquals("AT123456789012345678", konto.getIban());
        assertEquals("12345678", konto.getKontoNummer());
    }

    @Test
    void testUngueltigerInhaber() {
        assertThrows(IllegalArgumentException.class, () ->
            new Sparkonto("", new BigDecimal("100.00"), "AT123", "123")
        );
    }

    @Test
    void testEinzahlen() {
        Sparkonto konto = new Sparkonto("Max Mustermann", new BigDecimal("500.00"), "AT123456789012345678", "12345678");
        konto.einzahlen(new BigDecimal("200.00"));
        assertEquals(new BigDecimal("700.00"), konto.getKontoStand());
    }

    @Test
    void testAuszahlenErfolg() {
        Sparkonto konto = new Sparkonto("Max Mustermann", new BigDecimal("500.00"), "AT123456789012345678", "12345678");
        konto.auszahlen(new BigDecimal("150.00"));
        assertEquals(new BigDecimal("350.00"), konto.getKontoStand());
    }

    @Test
    void testAuszahlenUeberziehungNichtMoeglich() {
        Sparkonto konto = new Sparkonto("Max Mustermann", new BigDecimal("100.00"), "AT123456789012345678", "12345678");
        konto.auszahlen(new BigDecimal("150.00"));
        assertEquals(new BigDecimal("100.00"), konto.getKontoStand());
    }

    @Test
    void testUeberweisenErfolg() {
        Sparkonto konto1 = new Sparkonto("Max Mustermann", new BigDecimal("500.00"), "AT111111111111111111", "11111111");
        Sparkonto konto2 = new Sparkonto("Erika Musterfrau", new BigDecimal("200.00"), "AT222222222222222222", "22222222");

        boolean erfolg = konto1.transferAmount(konto2, new BigDecimal("100.00"));

        assertTrue(erfolg);
        assertEquals(new BigDecimal("400.00"), konto1.getKontoStand());
        assertEquals(new BigDecimal("300.00"), konto2.getKontoStand());
    }

    @Test
    void testUeberweisenFehlgeschlagen() {
        Sparkonto konto1 = new Sparkonto("Max Mustermann", new BigDecimal("50.00"), "AT111111111111111111", "11111111");
        Sparkonto konto2 = new Sparkonto("Erika Musterfrau", new BigDecimal("200.00"), "AT222222222222222222", "22222222");

        boolean erfolg = konto1.transferAmount(konto2, new BigDecimal("100.00"));

        assertFalse(erfolg);
        assertEquals(new BigDecimal("50.00"), konto1.getKontoStand());
        assertEquals(new BigDecimal("200.00"), konto2.getKontoStand());
    }
}
