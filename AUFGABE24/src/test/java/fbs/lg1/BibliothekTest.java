package fbs.lg1;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BibliothekTest {

    private Bibliothek bibliothek;
    private Buch buch;
    private Nutzer nutzer;

    // Vor jedem Test: neue Bibliothek mit einem Buch und einem Nutzer
    @BeforeEach
    void setUp() {
        bibliothek = new Bibliothek("Stadtbibliothek", "Hauptplatz 1, Klagenfurt");
        buch = new Buch("123", "Java lernen", "Felix Lassnig");
        nutzer = new Nutzer(1, "Felix Lassnig", "felix@lassnig.at");
    }

    // Test: Die Bibliothek startet mit Name, Adresse und leeren Listen
    @Test
    void testBibliothekErstellung() {
        assertThat(bibliothek.getName()).isEqualTo("Stadtbibliothek");
        assertThat(bibliothek.getAdresse()).isEqualTo("Hauptplatz 1, Klagenfurt");
        assertThat(bibliothek.getBuecher()).isEmpty();
        assertThat(bibliothek.getNutzer()).isEmpty();
        assertThat(bibliothek.getAusleihen()).isEmpty();
    }

    // Test: Ein neues Buch wird zum Bestand hinzugefügt
    @Test
    void testNeuesBuchHinzufuegen() {
        bibliothek.neuesBuchHinzufuegen(buch);

        assertThat(bibliothek.getBuecher()).containsExactly(buch);
    }

    // Test: Ein Nutzer wird registriert
    @Test
    void testNutzerRegistrieren() {
        bibliothek.nutzerRegistrieren(nutzer);

        assertThat(bibliothek.getNutzer()).containsExactly(nutzer);
    }

    // Test: Ein verfügbares Buch kann ausgeliehen werden
    @Test
    void testBuchAusleihen() {
        Ausleihe ausleihe = bibliothek.buchAusleihen(buch, nutzer, LocalDate.of(2026, 1, 1));

        assertThat(ausleihe).isNotNull();
        assertThat(bibliothek.getAusleihen()).containsExactly(ausleihe);
        assertThat(buch.isVerfuegbar()).isFalse();
    }

    // Test: Ein bereits ausgeliehenes Buch kann nicht nochmal ausgeliehen werden
    @Test
    void testBuchAusleihenNichtVerfuegbar() {
        bibliothek.buchAusleihen(buch, nutzer, LocalDate.of(2026, 1, 1));

        Ausleihe zweite = bibliothek.buchAusleihen(buch, nutzer, LocalDate.of(2026, 1, 2));

        assertThat(zweite).isNull();
        assertThat(bibliothek.getAusleihen()).hasSize(1);
    }
}
