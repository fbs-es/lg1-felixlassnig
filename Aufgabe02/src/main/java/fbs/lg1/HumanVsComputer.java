package fbs.lg1;

import java.util.Scanner;

public class HumanVsComputer {

    public void StartGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mensch vs Computer (Optimiert mit Binärer Suche)");
        System.out.println("Zahlenbereich 1 - 100");
        System.out.print("Gib eine Zahl zwischen 1 und 100 ein: ");
        int userNumber = scanner.nextInt();
        scanner.nextLine();

        int untergrenze = 1;
        int obergrenze = 100;
        int count = 0;
        boolean gefunden = false;

        while (!gefunden) {
            count++;

            if (untergrenze > obergrenze) {
                System.out.println(
                        "Logikfehler: Die Grenzen haben sich überschnitten. Sicher, dass du nicht gemogelt hast?");
                break;
            }

            int tipp = untergrenze + (obergrenze - untergrenze) / 2;

            System.out.println("\nVersuch " + count + ": Ist die Zahl: " + tipp + "?");

            if (tipp == userNumber) {
                System.out.println("Der Computer hat die Zahl " + tipp + " in " + count + " Versuchen gefunden.");
                gefunden = true;
            } else {
                System.out.print("Ist die gesuchte Zahl höher (h) oder niedriger (n)? ");
                String antwort = scanner.nextLine().toLowerCase();

                if (antwort.equals("h")) {
                    untergrenze = tipp + 1;
                } else if (antwort.equals("n")) {
                    obergrenze = tipp - 1;
                } else {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie 'h' oder 'n' ein.");
                    count--; // Versuch nicht zählen bei Falscheingabe
                }
            }
        }

        System.out.println("Das Spiel ist vorbei. Die gesuchte Zahl war: " + userNumber);
    }
}