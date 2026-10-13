package fbs.lg1;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class MitarbeiterverwaltungTest {

    private Mitarbeiterverwaltung verwaltung;
    private Angestellter angestellter;
    private Auszubildender azubi;
    private Aushilfe aushilfe;

    @BeforeEach
    void setUp() {
        verwaltung = new Mitarbeiterverwaltung("Lassnig IT Solutions Klagenfurt");

        // Testdaten mit regionalem Bezug zu Kaernten
        angestellter = new Angestellter("Felix", "Lassnig", 3000.0, 2022, 30);
        azubi = new Auszubildender("Anna", "Wurzer", 1000.0, "Applikationsentwicklung", 1);
        aushilfe = new Aushilfe("Lukas", "Kofler", 500.0, 15.0);

        verwaltung.mitarbeiterHinzufuegen(angestellter);
        verwaltung.mitarbeiterHinzufuegen(azubi);
        verwaltung.mitarbeiterHinzufuegen(aushilfe);
    }

    @Test
    void testMitarbeiterHinzufuegenUndAnzahl() {
        // Pruefen, ob alle drei Testmitarbeiter in der Verwaltung sind
        assertThat(verwaltung.getAnzahlMitarbeiter()).isEqualTo(3);
        assertThat(verwaltung.getMitarbeiterListe()).contains(angestellter, azubi, aushilfe);
    }

    @Test
    void testMitarbeiterEntfernen() {
        // Mitarbeiter entfernen
        boolean entfernt = verwaltung.mitarbeiterEntfernen(azubi);

        assertThat(entfernt).isTrue();
        assertThat(verwaltung.getAnzahlMitarbeiter()).isEqualTo(2);
        assertThat(verwaltung.getMitarbeiterListe()).doesNotContain(azubi);
    }

    @Test
    void testNullMitarbeiterHinzufuegenWirdIgnoriert() {
        boolean hinzugefuegt = verwaltung.mitarbeiterHinzufuegen(null);

        assertThat(hinzugefuegt).isFalse();
        assertThat(verwaltung.getAnzahlMitarbeiter()).isEqualTo(3);
    }

    @Test
    void testBerechneGesamtgehalt() {
        // 3000 + 1000 + 500 = 4500.0
        double summe = verwaltung.berechneGesamtgehalt();
        assertThat(summe).isEqualTo(4500.0);
    }

    @Test
    void testBerechneDurchschnittsgehalt() {
        // 4500.0 / 3 = 1500.0
        double schnitt = verwaltung.berechneDurchschnittsgehalt();
        assertThat(schnitt).isEqualTo(1500.0);
    }

    @Test
    void testSucheNachNachnameGefunden() {
        List<Mitarbeiter> ergebnis = verwaltung.sucheNachNachname("Lassnig");

        assertThat(ergebnis).hasSize(1);
        assertThat(ergebnis.get(0).getVorname()).isEqualTo("Felix");
    }

    @Test
    void testSucheNachNachnameNichtGefunden() {
        List<Mitarbeiter> ergebnis = verwaltung.sucheNachNachname("Mustermann");

        assertThat(ergebnis).isEmpty();
    }

    @Test
    void testFilterNachKategorie() {
        List<Mitarbeiter> angestellte = verwaltung.filterNachKategorie("Angestellter");
        List<Mitarbeiter> azubis = verwaltung.filterNachKategorie("Auszubildender");
        List<Mitarbeiter> aushilfen = verwaltung.filterNachKategorie("Aushilfe");

        assertThat(angestellte).containsExactly(angestellter);
        assertThat(azubis).containsExactly(azubi);
        assertThat(aushilfen).containsExactly(aushilfe);
    }

    @Test
    void testPolymorpheGehaltsanpassungFuerAlleMitarbeiter() {
        // Feste Anpassung mit Wert 10.0:
        // - Angestellter: +10% -> 3000 * 1.10 = 3300.0
        // - Azubi: +10 EUR -> 1000 + 10 = 1010.0
        // - Aushilfe: Stundenlohn auf 10.0 angepasst
        verwaltung.passeAlleGehaelterAn(10.0);

        assertThat(angestellter.getGehalt()).isCloseTo(3300.0, within(0.001));
        assertThat(azubi.getGehalt()).isEqualTo(1010.0);
        assertThat(aushilfe.getStundenlohn()).isEqualTo(10.0);
    }
}
