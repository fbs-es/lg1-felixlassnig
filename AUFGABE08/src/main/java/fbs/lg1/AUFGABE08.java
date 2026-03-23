package fbs.lg1;

import java.util.Scanner;

public class AUFGABE08 {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("PAKET-AUFGABESTATION");

        System.out.print("Sensormessung - Gewicht in kg: ");
        double gewicht = scanner.nextDouble();

        System.out.print("Sind die Masse in der Norm" + " (true/false)");
        boolean masseOk = scanner.nextBoolean();

        if (masseOk) {
            System.out.println("Masse in Ordnung, weiter mit der Kundeneingabe.");
        } else {
            System.out.println("Masse außerhalb der Norm, bitte überprüfen Sie die Sensoren.");
            return;
        }

        if (gewicht > 31.5) {
            System.out.println("GRUND: Paket zu schwer alles über 31.5kg ist außerhalb der Norm.");
            System.out.println("HINWEIS: Bitte geben Sie das Paket persönlich am Schalter ab.");
            return;
        } else if (gewicht <= 0) {
            System.out.println("Ungültiges Gewicht. Bitte überprüfen Sie die Sensoren.");
            return;
        }

        System.out.print("Enthält das Paket Gefahrstoffe? (true/false): ");
        boolean hatGefahrstoffe = scanner.nextBoolean();

        if (hatGefahrstoffe) {
            // Gefahrgut führt immer zum Abbruch
            System.out.println("ERGEBNIS: Annahme verweigert!");
            System.out.println("GRUND: Gefahrstoffe dürfen nicht am Automaten abgegeben werden.");
            return;
        }

        System.out.print("Ist der Inhalt zerbrechlich? (true/false): ");
        boolean istZerbrechlich = scanner.nextBoolean();

        if (istZerbrechlich) {
            System.out.println("VERMERK: [VORSICHT ZERBRECHLICH] für Sortieranlage gespeichert.");
        }

        System.out.println("ERGEBNIS: Paket angenommen.");
        System.out.println("STATUS: Versandbestätigung wird gedruckt...");

        scanner.close();
    }
}