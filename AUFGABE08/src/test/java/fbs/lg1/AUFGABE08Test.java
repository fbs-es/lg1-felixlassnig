package fbs.lg1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fbs.lg1.pruefen.EvaluatePaket;

class AUFGABE08Test {

    @ParameterizedTest
    @CsvSource({
            "20.0, true, false, false, Paket angenommen",
            "33.0, true, false, false, Paket zu schwer",
            "-1.0, true, false, false, Ungültiges Gewicht",
            "20.0, false, false, false, Masse außerhalb der Norm",
            "20.0, true, true, false, Gefahrstoffe nicht erlaubt",
            "20.0, true, false, true, Paket angenommen mit Hinweis zerbrechlich" })
    void testEvaluatePaket(double gewicht, boolean masseOk, boolean hatGefahrstoffe, boolean istZerbrechlich,
            String erwartet) {
        EvaluatePaket aufgabe = new EvaluatePaket();
        String actual = aufgabe.evaluatePaket(gewicht, masseOk, hatGefahrstoffe, istZerbrechlich);
        assertEquals(erwartet, actual);
    }
}
