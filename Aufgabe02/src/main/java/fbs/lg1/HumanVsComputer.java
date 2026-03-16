package fbs.lg1;

import java.util.Scanner;

public class HumanVsComputer {

    public void StartGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mensch vs Computer");
        System.out.println("Zahlenbereich 1 - 100");

        // User gibt eine Zahl am Anfang ein
        System.out.print("Gib eine Zahl zwischen 1 und 100 ein: ");
        int userNumber = scanner.nextInt();
        scanner.nextLine();

        // Ober und Untergrenze festlegen
        int obergrenze = 100;
        int untergrenze = 1;
        int count = 0;
        boolean gefunden = false;

        while (!gefunden) {
            count++;

            if (untergrenze > obergrenze) {
                System.out.println("Logikfehler");
                break;
            }

            int tipp = GenRandom.generierenRandom(untergrenze, obergrenze);

            System.out.println("\nVersuch " + count + ": Ist die Zahl: " + tipp);

            if (tipp == userNumber) {
                System.out.println("Die Zahl " + tipp + " wurde in " + count + " Versuchen gefunden.");
                gefunden = true;
            } else {
                System.out.println("Ungültige Antwort. Bitte geben Sie 'h' für höher, 'n' für niedriger ein.");
                String antwort = scanner.nextLine().toLowerCase();

                if (antwort.equals("h")) {
                    untergrenze = tipp + 1;
                } else if (antwort.equals("n")) {
                    obergrenze = tipp - 1;
                } else {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie 'h' oder 'n' ein.");
                    count--;
                }
            }
        }

        System.out.println("Das Spiel ist vorbei. Die gesuchte Zahl war: " + userNumber);
        scanner.close();
    }
}
