package fbs.lg1;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ArbeitszeitTest {

    @Test
    void testArbeitszeitErstellungStandardNichtAbgerechnet() {
        LocalDate datum = LocalDate.of(2026, 10, 10);
        Arbeitszeit az = new Arbeitszeit(datum, 90);

        assertThat(az.getDatum()).isEqualTo(datum);
        assertThat(az.getZeitInMinuten()).isEqualTo(90);
        assertThat(az.isAbgerechnet()).isFalse();
        assertThat(az.getZeitInStunden()).isEqualTo(1.5);
    }

    @Test
    void testArbeitszeitAlsAbgerechnetMarkieren() {
        Arbeitszeit az = new Arbeitszeit(LocalDate.now(), 60);
        az.alsAbgerechnetMarkieren();

        assertThat(az.isAbgerechnet()).isTrue();
    }
}
