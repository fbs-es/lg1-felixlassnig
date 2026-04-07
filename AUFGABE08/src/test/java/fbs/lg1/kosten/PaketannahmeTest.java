package fbs.lg1.kosten;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PaketannahmeTest {
    static Paketannahme pruefen;

    @BeforeAll
    static void setUpClass() {
        pruefen = new Paketannahme();
    }

    @Test
    void AblehnenTestTrue() {
        assertEquals(EnumAbnahme.ABLEHNEN, pruefen.annahmePruefen(true, false, false, true));
    }

    @Test
    void AblehnenTestFail() {
        assertEquals(EnumAbnahme.ANNAHME, pruefen.annahmePruefen(false, false, false, false));
    }

    @Test
    void AnnahmeTestTrue() {
        assertEquals(EnumAbnahme.ANNAHME, pruefen.annahmePruefen(false, false, false, false));
    }

    @Test
    void AnnahmeTestFail() {
        assertEquals(EnumAbnahme.ABLEHNEN, pruefen.annahmePruefen(true, false, false, true));
    }

    @Test
    void SperrgutTestTrue() {
        assertEquals(EnumAbnahme.SPERRGUT, pruefen.annahmePruefen(false, true, false, false));
    }

    @Test
    void SperrgutTestFail() {
        assertEquals(EnumAbnahme.ANNAHME, pruefen.annahmePruefen(false, false, false, false));
    }

    @Test
    void AnnahmeZerbrechlichTestTrue() {
        assertEquals(EnumAbnahme.ANNAHME_ZERBRECHLICH, pruefen.annahmePruefen(false, false, false, true));
    }

    @Test
    void AnnahmeZerbrechlichTestFail() {
        assertEquals(EnumAbnahme.ANNAHME, pruefen.annahmePruefen(false, false, false, false));
    }
}