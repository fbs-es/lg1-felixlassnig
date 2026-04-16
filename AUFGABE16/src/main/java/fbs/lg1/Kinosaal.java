package fbs.lg1;

public class Kinosaal {
    private final char[][] sitzplan;
    private final int reihen;
    private final int plaetze;

    // Konstanten für die Sitzzustände (intern)
    public static final char FREI = 'F';
    public static final char RESERVIERT = 'R';
    public static final char VORGEMERKT = 'V';

    public Kinosaal(int r, int p) {
        this.reihen = r;
        this.plaetze = p;
        this.sitzplan = new char[r][p];
        init();
    }

    private void init() {
        for (int i = 0; i < reihen; i++) {
            for (int j = 0; j < plaetze; j++) {
                // Letzte Reihe ist standardmäßig reserviert
                sitzplan[i][j] = (i == reihen - 1) ? RESERVIERT : FREI;
            }
        }
    }

    // Getter und Setter für die Logik
    public char getStatus(int r, int p) {
        return sitzplan[r][p];
    }

    public void setStatus(int r, int p, char status) {
        sitzplan[r][p] = status;
    }

    public int getAnzahlReihen() {
        return reihen;
    }

    public int getAnzahlPlaetze() {
        return plaetze;
    }
}
