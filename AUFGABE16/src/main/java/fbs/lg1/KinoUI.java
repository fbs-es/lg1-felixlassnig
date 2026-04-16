package fbs.lg1;

import java.util.Map;

public class KinoUI {
    private final KinoService service;
    private final Kinosaal saal;

    public KinoUI(KinoService service, Kinosaal saal) {
        this.service = service;
        this.saal = saal;
    }

    public void zeigeSaal() {
        System.out.println("\n--- AKTUELLER SITZPLAN ---");
        System.out.print("      ");
        for (int s = 0; s < saal.getAnzahlPlaetze(); s++) {
            System.out.print(s + " ");
        }
        System.out.println("\n-----------------------");

        for (int i = 0; i < saal.getAnzahlReihen(); i++) {
            System.out.printf("R %02d | ", i);
            for (int j = 0; j < saal.getAnzahlPlaetze(); j++) {
                System.out.print(saal.getStatus(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void zeigeStatistik() {
        Map<Character, Integer> daten = service.getStatistikDaten();

        System.out.println("\n--- KINO STATISTIK ---");
        System.out.println("Freie Plätze:      " + daten.get(Kinosaal.FREI));
        System.out.println("Reservierte Sitze: " + daten.get(Kinosaal.RESERVIERT));
        System.out.println("Vorgemerkt:        " + daten.get(Kinosaal.VORGEMERKT));
        System.out.println("----------------------");
    }
}