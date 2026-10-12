package fbs.lg1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class NutzerTest {

    // Test: Ein Nutzer wird mit allen Attributen korrekt angelegt
    @Test
    void testNutzerErstellung() {
        Nutzer nutzer = new Nutzer(1, "Felix Lassnig", "felix@lassnig.at");

        assertThat(nutzer.getUserId()).isEqualTo(1);
        assertThat(nutzer.getName()).isEqualTo("Felix Lassnig");
        assertThat(nutzer.getEmail()).isEqualTo("felix@lassnig.at");
    }
}
