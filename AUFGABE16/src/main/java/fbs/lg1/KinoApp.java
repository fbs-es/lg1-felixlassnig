package fbs.lg1;

public class KinoApp {
    public static void main(String[] args) {
        Kinosaal saal = new Kinosaal(13, 9);
        KinoService service = new KinoService(saal);

        KinoUI ui = new KinoUI(service, saal);
        ui.zeigeSaal();

        int[][] vorschlag = service.sucheFreiePlaetze(3);
        if (vorschlag.length > 0) {
            System.out.println("\n[SYSTEM] 3 Plätze gefunden und vorgemerkt.");
            service.vorschlagAnnehmen(vorschlag); // oder ablehnen
        }

        ui.zeigeSaal();
        ui.zeigeStatistik();
    }
}