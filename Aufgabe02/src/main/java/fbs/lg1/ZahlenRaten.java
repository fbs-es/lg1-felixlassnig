package fbs.lg1;

import java.util.Scanner;

public class ZahlenRaten {

    public void spielStarten() {
        GenRandom randomGen = new GenRandom();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Willkommen zum Zahlenraten!");
        System.out.println("Zahlenbereich 1 - 100");
        int randomZahl = randomGen.generierenRandom(1, 100);
        int count = 0;
        boolean ausgabe = true;
        String warmKaltRueckmeldung = "";
        String rueckMeldungGewinn = "";

        while (ausgabe) {
            count++;
            System.out.println("Geben Sie eine Zahl ein:");
            int benutzerZahl = scanner.nextInt();
            scanner.nextLine();

            if (benutzerZahl == randomZahl) {
                ausgabe = false;
            }
            warmKaltRueckmeldung = this.warmKalt(randomZahl, benutzerZahl);
            rueckMeldungGewinn = this.rueckmeldungGewinn(benutzerZahl, randomZahl);

            System.out.println("Rückmeldung: " + warmKaltRueckmeldung + " Versuche: " + count + " Rückmeldung Gewinn: "
                    + rueckMeldungGewinn);
        }
        System.out.println(
                "Die gesuchte Zahl war: " + randomZahl + "\nWarm Kalt: " + warmKaltRueckmeldung + "\nVersuche: "
                        + count);
    }

    public String warmKalt(int randomZahl, int benutzerZahl) {
        String rueckmeldung = "Kalt";
        int ergebnis = randomZahl - benutzerZahl;

        if (ergebnis <= 5) {
            rueckmeldung = "Warm";

        }
        if (ergebnis == 0) {
            rueckmeldung = "Heiß";
        }
        return rueckmeldung;
    }

    public String rueckmeldungGewinn(int benutzerZahl, int randomZahl) {
        String rueckmeldung = "";
        if (randomZahl < benutzerZahl) {
            rueckmeldung = "Kleiner";
        } else {
            rueckmeldung = "Größer";
        }
        if (randomZahl == benutzerZahl) {
            rueckmeldung = "Gewonnen";
        }
        return rueckmeldung;
    }

}