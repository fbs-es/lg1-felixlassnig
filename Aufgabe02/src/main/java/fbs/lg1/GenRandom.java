package fbs.lg1;

import java.util.Random;

public class GenRandom {
    private Random random = new Random();

    @Deprecated
    public int generierenRandom(int obergrenze) {
        return random.nextInt(obergrenze);
    }

    // Das ist die neue, verbesserte Methode für den Bereich [min, max]
    public int generierenRandom(int untergrenze, int obergrenze) {
        // Formel: Spanne berechnen (+1 für inklusive) und Startwert addieren
        return random.nextInt(obergrenze - untergrenze + 1) + untergrenze;
    }
}