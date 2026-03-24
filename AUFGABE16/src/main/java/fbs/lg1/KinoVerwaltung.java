package fbs.lg1;

public class KinoVerwaltung {

    private static final int REIHEN = 13;
    private static final int PLAETZE = 9;
    private char[][] kinosaal;

    public KinoVerwaltung() {
        this(REIHEN, PLAETZE);
    }

    public KinoVerwaltung(int r, int p) {
        kinosaal = new char[r][p];
        init();
    }

    private void init() {
        for (int i = 0; i < kinosaal.length; i++) {

            for (int j = 0; j < kinosaal[i].length; j++) {

                if (i == kinosaal.length - 1) {
                    kinosaal[i][j] = 'R'; // reserviert
                } else {
                    kinosaal[i][j] = 'F'; // frei
                }
            }
        }
    }

    public void printKinosaal() {
        for (int i = 0; i < kinosaal.length; i++) {
            for (int j = 0; j < kinosaal[i].length; j++) {
                System.out.print(kinosaal[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void start() {
        printKinosaal();
    }

    public char[][] getKinosaal() {
        return this.kinosaal;
    }
}
