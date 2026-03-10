package fbs.lg1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RandomTest {
    @ParameterizedTest
    @CsvSource({
            "1, 100",
            "50, 100",
            "50, 50",
            "-100, -50", })
    void testGenerierenRandom(int min, int max) {
        GenRandom gr = new GenRandom();
        int actual = gr.generierenRandom(min, max);
        assertTrue(actual >= min && actual <= max);
    }
}