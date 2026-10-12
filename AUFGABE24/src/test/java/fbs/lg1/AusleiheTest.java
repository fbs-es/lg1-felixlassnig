package fbs.lg1;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AusleiheTest {

    private Buch buch;
    private Nutzer nutzer;
    private Ausleihe ausleihe;
    private final LocalDate start = LocalDate.of(2026, 1, 1);

    // Vor jedem Test: frisches Buch, Nutzer und eine neue Ausleihe anlegen
    @BeforeEach
    void setUp() {
        buch = new Buch("123", "Java lernen", "Felix Lassnig");
        nutzer = new Nutzer(1, "Felix Lassnig", "felix@lassnig.at");
        ausleihe = new Ausleihe(buch, nutzer, start);
    }

    // Test: Neue Ausleihe hat Startwerte und Rückgabe nach 14 Tagen
    @Test
    void testAusleiheErstellung() {
        assertThat(ausleihe.getGeplantesRueckgabedatum()).isEqualTo(start.plusDays(14));
        assertThat(ausleihe.getStatus()).isEqualTo(Ausleihstatus.AKTIV);
        assertThat(ausleihe.isVerlaengert()).isFalse();
        assertThat(ausleihe.getVerspaetungsStrafe()).isEqualTo(0.0);
    }

    // Test: Beim Ausleihen wird das Buch nicht mehr verfügbar
    @Test
    void testBuchIstNachAusleiheNichtVerfuegbar() {
        assertThat(buch.isVerfuegbar()).isFalse();
    }

    // Test: Die erste Verlängerung klappt und schiebt das Datum um 14 Tage
    @Test
    void testErsteVerlaengerung() {
        boolean ergebnis = ausleihe.verlaengern();

        assertThat(ergebnis).isTrue();
        assertThat(ausleihe.isVerlaengert()).isTrue();
        assertThat(ausleihe.getGeplantesRueckgabedatum()).isEqualTo(start.plusDays(28));
    }

    // Test: Eine zweite Verlängerung wird abgelehnt
    @Test
    void testZweiteVerlaengerungAbgelehnt() {
        ausleihe.verlaengern();

        boolean ergebnis = ausleihe.verlaengern();

        assertThat(ergebnis).isFalse();
        assertThat(ausleihe.getGeplantesRueckgabedatum()).isEqualTo(start.plusDays(28));
    }

    // Test: Pünktliche Rückgabe ergibt keine Strafe
    @Test
    void testRueckgabePuenktlich() {
        ausleihe.rueckgabe(start.plusDays(14));

        assertThat(ausleihe.getVerspaetungsStrafe()).isEqualTo(0.0);
        assertThat(ausleihe.getStatus()).isEqualTo(Ausleihstatus.ZURUECKGEGEBEN);
        assertThat(buch.isVerfuegbar()).isTrue();
    }

    // Test: 3 Tage zu spät ergibt 3,00 € Strafe
    @Test
    void testRueckgabeVerspaetet() {
        ausleihe.rueckgabe(start.plusDays(17));

        assertThat(ausleihe.getVerspaetungsStrafe()).isEqualTo(3.0);
        assertThat(ausleihe.getStatus()).isEqualTo(Ausleihstatus.ZURUECKGEGEBEN);
    }

    // Test: Nach Ablauf der Frist wird der Status auf überzogen gesetzt
    @Test
    void testPruefeUeberzogen() {
        ausleihe.pruefeUeberzogen(start.plusDays(15));

        assertThat(ausleihe.getStatus()).isEqualTo(Ausleihstatus.UEBERZOGEN);
    }

    // Test: Innerhalb der Frist bleibt der Status aktiv
    @Test
    void testPruefeNichtUeberzogen() {
        ausleihe.pruefeUeberzogen(start.plusDays(10));

        assertThat(ausleihe.getStatus()).isEqualTo(Ausleihstatus.AKTIV);
    }
}
