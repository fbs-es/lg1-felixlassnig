package fbs.lg1;

import java.util.Random;

public class GenRandom {

    private static final Random random = new Random();

    @Deprecated
    public static int generierenRandom(int obergrenze) {
        return random.nextInt(obergrenze);
    }

    // Das ist die neue, verbesserte Methode für den Bereich [min, max]
    public static int generierenRandom(int untergrenze, int obergrenze) {
        return random.nextInt(obergrenze - untergrenze + 1) + untergrenze;
    }
}