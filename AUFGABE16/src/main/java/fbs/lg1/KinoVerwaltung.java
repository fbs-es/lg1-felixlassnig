package fbs.lg1;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasse zur Verwaltung eines Kinosaals.
 * Enthält Funktionen zur Reservierung, Vormerkung von Blockplätzen und
 * Statistik.
 */
public class KinoVerwaltung {

    // Konstanten für die Standardmaße
    private static final int STANDARD_REIHEN = 13;
    private static final int STANDARD_PLAETZE = 9;

    // Konstanten für die Sitzzustände
    private static final char FREI = 'F';
    private static final char RESERVIERT = 'R';
    private static final char VORGEMERKT = 'V';

    private char[][] kinosaal;

    /**
     * Standardkonstruktor mit 13 Reihen und 9 Plätzen.
     */
    public KinoVerwaltung() {
        this(STANDARD_REIHEN, STANDARD_PLAETZE);
    }

    /**
     * Konstruktor für individuelle Saalgrößen.
     * 
     * @param r Anzahl der Reihen
     * @param p Anzahl der Plätze pro Reihe
     */
    public KinoVerwaltung(int r, int p) {
        kinosaal = new char[r][p];
        init();
    }

    /**
     * Initialisiert den Saal: Alle frei, letzte Reihe reserviert (Abo).
     */
    private void init() {
        for (int i = 0; i < kinosaal.length; i++) {
            for (int j = 0; j < kinosaal[i].length; j++) {
                if (i == kinosaal.length - 1) {
                    kinosaal[i][j] = RESERVIERT;
                } else {
                    kinosaal[i][j] = FREI;
                }
            }
        }
    }

    /**
     * Zeigt die aktuelle Belegung des Kinosaals grafisch an.
     */
    public void printKinosaal() {
        System.out.println("\n--- AKTUELLER SITZPLAN ---");
        System.out.print("      ");
        for (int s = 0; s < kinosaal[0].length; s++) {
            System.out.print(s + " ");
        }
        System.out.println("\n-----------------------");

        for (int i = 0; i < kinosaal.length; i++) {
            System.out.printf("R %02d | ", i);
            for (int j = 0; j < kinosaal[i].length; j++) {
                System.out.print(kinosaal[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Reserviert einen einzelnen Sitzplatz.
     * 
     * @return true, wenn erfolgreich reserviert wurde.
     */
    public boolean reserviereSitz(int reihe, int platz) {
        if (reihe >= 0 && reihe < kinosaal.length && platz >= 0 && platz < kinosaal[0].length) {
            if (kinosaal[reihe][platz] == FREI) {
                kinosaal[reihe][platz] = RESERVIERT;
                return true;
            }
        }
        return false;
    }

    /**
     * FREIWILLIGE AUFGABE: Suche nach nebeneinanderliegenden Plätzen (max. 3).
     * Markiert die Plätze sofort als VORGEMERKT ('V').
     * 
     * @param anzahl Gewünschte Plätze (1-3)
     * @return Koordinaten-Array {{R, S}, {R, S}} oder null, wenn nichts gefunden.
     */
    public int[][] sucheFreiePlaetze(int anzahl) {
        if (anzahl < 1 || anzahl > 3)
            return new int[0][0];

        for (int i = 0; i < kinosaal.length; i++) {
            for (int j = 0; j <= kinosaal[i].length - anzahl; j++) {
                boolean blockFrei = true;

                for (int k = 0; k < anzahl; k++) {
                    if (kinosaal[i][j + k] != FREI) {
                        blockFrei = false;
                        break;
                    }
                }

                if (blockFrei) {
                    int[][] vorschlag = new int[anzahl][2];
                    for (int k = 0; k < anzahl; k++) {
                        kinosaal[i][j + k] = VORGEMERKT;
                        vorschlag[k][0] = i;
                        vorschlag[k][1] = j + k;
                    }
                    return vorschlag;
                }
            }
        }
        return new int[0][0];
    }

    /**
     * Nimmt den Vorschlag an und macht aus 'V' ein 'R'.
     */
    public void vorschlagAnnehmen(int[][] vorschlag) {
        if (vorschlag == null)
            return;
        for (int[] pos : vorschlag) {
            if (kinosaal[pos[0]][pos[1]] == VORGEMERKT) {
                kinosaal[pos[0]][pos[1]] = RESERVIERT;
            }
        }
        System.out.println("Reservierung abgeschlossen.");
    }

    /**
     * Lehnt den Vorschlag ab und macht aus 'V' wieder 'F'.
     */
    public void vorschlagAblehnen(int[][] vorschlag) {
        if (vorschlag == null)
            return;
        for (int[] pos : vorschlag) {
            if (kinosaal[pos[0]][pos[1]] == VORGEMERKT) {
                kinosaal[pos[0]][pos[1]] = FREI;
            }
        }
        System.out.println("Vorschlag abgelehnt, Plätze wieder frei.");
    }

    /**
     * Erstellt eine Statistik über alle Zustände mittels Map.
     */
    public void printStatistik() {
        Map<Character, Integer> stats = new HashMap<>();
        stats.put(FREI, 0);
        stats.put(RESERVIERT, 0);
        stats.put(VORGEMERKT, 0);

        for (char[] reihe : kinosaal) {
            for (char sitz : reihe) {
                stats.put(sitz, stats.get(sitz) + 1);
            }
        }

        System.out.println("\n--- KINO STATISTIK ---");
        System.out.println("Frei (F):       " + stats.get(FREI));
        System.out.println("Reserviert (R): " + stats.get(RESERVIERT));
        System.out.println("Vorgemerkt (V): " + stats.get(VORGEMERKT));
    }

    public void start() {
        printKinosaal();
        printStatistik();
    }

    public char[][] getKinosaal() {
        return this.kinosaal;
    }
}