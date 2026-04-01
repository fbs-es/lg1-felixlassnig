package fbs.lg1.kosten;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class VersandRechnerTest {

    static PreisRechnerVersand rechner;

    @BeforeAll
    static void Setup() {
        rechner = new PreisRechnerVersand();
    }

    @Test
    void sInlandNormal() {
        assertEquals(5, rechner.versandRechner(true, true, Basispreis.S.getBasispreis()));
    }

    @Test
    void sInlandNormalFail() {
        assertEquals(1, rechner.versandRechner(true, true, Basispreis.S.getBasispreis()));
    }

    @Test
    void mInlandExpress() {
        assertEquals(20, rechner.versandRechner(true, false, Basispreis.M.getBasispreis()));
    }

    @Test
    void mInlandExpressFail() {
        assertEquals(1, rechner.versandRechner(true, false, Basispreis.M.getBasispreis()));
    }

    @Test
    void lAuslandNormal() {
        assertEquals(25, rechner.versandRechner(false, true, Basispreis.L.getBasispreis()));
    }

    @Test
    void lAuslandNormalFail() {
        assertEquals(1, rechner.versandRechner(false, true, Basispreis.L.getBasispreis()));
    }

    @Test
    void sAuslandExpress() {
        assertEquals(30, rechner.versandRechner(false, false, Basispreis.S.getBasispreis()));
    }

    @Test
    void sAuslandExpressFail() {
        assertEquals(1, rechner.versandRechner(false, false, Basispreis.S.getBasispreis()));
    }
}