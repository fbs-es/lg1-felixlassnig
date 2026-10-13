package fbs.lg1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AuszubildenderTest {

    private Auszubildender azubi;

    @BeforeEach
    void setUp() {
        azubi = new Auszubildender("Anna", "Wurzer", 950.0, "Applikationsentwicklung", 1);
    }

    @Test
    void testAuszubildenderErstellungUndGetter() {
        assertThat(azubi.getVorname()).isEqualTo("Anna");
        assertThat(azubi.getNachname()).isEqualTo("Wurzer");
        assertThat(azubi.getVollerName()).isEqualTo("Anna Wurzer");
        assertThat(azubi.getGehalt()).isEqualTo(950.0);
        assertThat(azubi.getAusbildungsberuf()).isEqualTo("Applikationsentwicklung");
        assertThat(azubi.getAktuellesAusbildungsjahr()).isEqualTo(1);
        assertThat(azubi.getKategorie()).isEqualTo("Auszubildender");
    }

    @Test
    void testPasseGehaltAnMitFestbetrag() {
        // Pauschale Erhöhung um 100 EUR
        azubi.passeGehaltAn(100.0);
        assertThat(azubi.getGehalt()).isEqualTo(1050.0);
    }

    @Test
    void testRueckeInNaechstesLehrjahr() {
        // Sprung ins 2. Lehrjahr mit 150 EUR Erhöhung
        azubi.rueckeInNaechstesLehrjahr(150.0);

        assertThat(azubi.getAktuellesAusbildungsjahr()).isEqualTo(2);
        assertThat(azubi.getGehalt()).isEqualTo(1100.0);
    }
}
