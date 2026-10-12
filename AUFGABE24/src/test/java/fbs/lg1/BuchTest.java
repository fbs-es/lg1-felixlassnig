package fbs.lg1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BuchTest {

    // Test: Ein neues Buch hat die richtigen Daten und ist anfangs verfügbar
    @Test
    void testBuchErstellung() {
        Buch buch = new Buch("123", "Java lernen", "Felix Lassnig");

        assertThat(buch.getISBN()).isEqualTo("123");
        assertThat(buch.getTitle()).isEqualTo("Java lernen");
        assertThat(buch.getAutor()).isEqualTo("Felix Lassnig");
        assertThat(buch.isVerfuegbar()).isTrue();
    }

    // Test: Die Verfügbarkeit kann geändert werden
    @Test
    void testSetVerfuegbar() {
        Buch buch = new Buch("123", "Java lernen", "Felix Lassnig");

        buch.setVerfuegbar(false);

        assertThat(buch.isVerfuegbar()).isFalse();
    }
}
