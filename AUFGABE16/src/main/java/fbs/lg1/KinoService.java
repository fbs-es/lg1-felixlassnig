package fbs.lg1;

import java.util.HashMap;
import java.util.Map;

public class KinoService {
    private final Kinosaal saal;

    public KinoService(Kinosaal saal) {
        this.saal = saal;
    }

    public int[][] sucheFreiePlaetze(int anzahl) {
        if (anzahl < 1 || anzahl > 3)
            return new int[0][0];

        for (int i = 0; i < saal.getAnzahlReihen(); i++) {
            for (int j = 0; j <= saal.getAnzahlPlaetze() - anzahl; j++) {
                if (istBlockFrei(i, j, anzahl)) {
                    return reserviereVorschlag(i, j, anzahl);
                }
            }
        }
        return new int[0][0];
    }

    private boolean istBlockFrei(int r, int startP, int anzahl) {
        for (int k = 0; k < anzahl; k++) {
            if (saal.getStatus(r, startP + k) != Kinosaal.FREI)
                return false;
        }
        return true;
    }

    private int[][] reserviereVorschlag(int r, int startP, int anzahl) {
        int[][] vorschlag = new int[anzahl][2];
        for (int k = 0; k < anzahl; k++) {
            saal.setStatus(r, startP + k, Kinosaal.VORGEMERKT);
            vorschlag[k][0] = r;
            vorschlag[k][1] = startP + k;
        }
        return vorschlag;
    }

    public void vorschlagAnnehmen(int[][] vorschlag) {
        processVorschlag(vorschlag, Kinosaal.RESERVIERT);
        System.out.println("Reservierung abgeschlossen.");
    }

    public void vorschlagAblehnen(int[][] vorschlag) {
        processVorschlag(vorschlag, Kinosaal.FREI);
        System.out.println("Vorschlag abgelehnt, Plätze wieder frei.");
    }

    private void processVorschlag(int[][] vorschlag, char neuerStatus) {
        if (vorschlag == null)
            return;
        for (int[] pos : vorschlag) {
            if (saal.getStatus(pos[0], pos[1]) == Kinosaal.VORGEMERKT) {
                saal.setStatus(pos[0], pos[1], neuerStatus);
            }
        }
    }

    public Map<Character, Integer> getStatistikDaten() {
        Map<Character, Integer> stats = new HashMap<>();
        stats.put(Kinosaal.FREI, 0);
        stats.put(Kinosaal.RESERVIERT, 0);
        stats.put(Kinosaal.VORGEMERKT, 0);

        for (int i = 0; i < saal.getAnzahlReihen(); i++) {
            for (int j = 0; j < saal.getAnzahlPlaetze(); j++) {
                char s = saal.getStatus(i, j);
                stats.put(s, stats.get(s) + 1);
            }
        }
        return stats;
    }
}