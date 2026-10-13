package fbs.lg1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class AngestellterTest {

    private Angestellter angestellter;

    @BeforeEach
    void setUp() {
        angestellter = new Angestellter("Felix", "Lassnig", 3500.0, 2020, 25);
    }

    @Test
    void testAngestellterErstellungUndGetter() {
        assertThat(angestellter.getVorname()).isEqualTo("Felix");
        assertThat(angestellter.getNachname()).isEqualTo("Lassnig");
        assertThat(angestellter.getVollerName()).isEqualTo("Felix Lassnig");
        assertThat(angestellter.getGehalt()).isEqualTo(3500.0);
        assertThat(angestellter.getEinstellungsjahr()).isEqualTo(2020);
        assertThat(angestellter.getUrlaubsanspruch()).isEqualTo(25);
        assertThat(angestellter.getKategorie()).isEqualTo("Angestellter");
    }

    @Test
    void testBerechneDienstjahre() {
        // Bei aktuellem Jahr 2026 und Eintritt 2020: 6 Dienstjahre
        int dienstjahre = angestellter.berechneDienstjahre(2026);
        assertThat(dienstjahre).isEqualTo(6);
    }

    @Test
    void testPasseGehaltAnProzentual() {
        // +10 Prozent Erhöhung von 3500 auf 3850
        angestellter.passeGehaltAn(10.0);
        assertThat(angestellter.getGehalt()).isCloseTo(3850.0, within(0.001));
    }

    @Test
    void testUrlaubsanspruchAendern() {
        angestellter.setUrlaubsanspruch(30);
        assertThat(angestellter.getUrlaubsanspruch()).isEqualTo(30);
    }
}
