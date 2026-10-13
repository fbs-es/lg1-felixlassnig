package fbs.lg1;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class AushilfeTest {

    private Aushilfe aushilfe;

    @BeforeEach
    void setUp() {
        aushilfe = new Aushilfe("Lukas", "Kofler", 0.0, 15.0);
    }

    @Test
    void testAushilfeErstellungUndGetter() {
        assertThat(aushilfe.getVorname()).isEqualTo("Lukas");
        assertThat(aushilfe.getNachname()).isEqualTo("Kofler");
        assertThat(aushilfe.getVollerName()).isEqualTo("Lukas Kofler");
        assertThat(aushilfe.getGehalt()).isEqualTo(0.0);
        assertThat(aushilfe.getStundenlohn()).isEqualTo(15.0);
        assertThat(aushilfe.getKategorie()).isEqualTo("Aushilfe");
        assertThat(aushilfe.getArbeitszeiten()).isEmpty();
    }

    @Test
    void testArbeitszeitHinzufuegenUndOffeneZeiten() {
        // 90 Minuten + 120 Minuten = 210 Minuten = 3.5 Stunden
        aushilfe.arbeitszeitHinzufuegen(new Arbeitszeit(LocalDate.of(2026, 10, 1), 90));
        aushilfe.arbeitszeitHinzufuegen(new Arbeitszeit(LocalDate.of(2026, 10, 2), 120));

        assertThat(aushilfe.getArbeitszeiten()).hasSize(2);
        assertThat(aushilfe.getOffeneArbeitsminuten()).isEqualTo(210);
        assertThat(aushilfe.getOffeneArbeitsstunden()).isEqualTo(3.5);
    }

    @Test
    void testRechneOffeneArbeitszeitenAb() {
        // 2 Stunden (120 Min) @ 15.0 EUR/h = 30 EUR
        aushilfe.arbeitszeitHinzufuegen(new Arbeitszeit(LocalDate.of(2026, 10, 1), 120));

        double auszahlung = aushilfe.rechneOffeneArbeitszeitenAb();

        assertThat(auszahlung).isEqualTo(30.0);
        assertThat(aushilfe.getGehalt()).isEqualTo(30.0);
        assertThat(aushilfe.getOffeneArbeitsminuten()).isEqualTo(0);
        assertThat(aushilfe.getArbeitszeiten().get(0).isAbgerechnet()).isTrue();
    }

    @Test
    void testPasseGehaltAnMitNeuemStundenlohn() {
        // Bei neuem Stundenlohn 20 EUR/h und 60 Min Arbeit:
        aushilfe.arbeitszeitHinzufuegen(new Arbeitszeit(LocalDate.of(2026, 10, 1), 60));

        aushilfe.passeGehaltAn(20.0);

        assertThat(aushilfe.getStundenlohn()).isEqualTo(20.0);
        assertThat(aushilfe.getGehalt()).isCloseTo(20.0, within(0.001));
        assertThat(aushilfe.getOffeneArbeitsminuten()).isEqualTo(0);
    }
}
