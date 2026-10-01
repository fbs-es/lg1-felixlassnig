package fbs.lg1;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // 1. Kunde und Roller erstellen
        Kunde kunde = new Kunde(1, "Max Mustermann", "max@schule.at", 15.0);
        Scooter scooter = new Scooter("SC-001");

        // 2. Roller ausleihen mit Startzeit
        LocalDateTime start = LocalDateTime.now();
        kunde.rollerAusleihen(scooter, start);
        System.out.println("Roller ausgeliehen. Entsperrt: " + !scooter.isLocked());

        // 3. Fahrt beenden (simulierte 20 Minuten Fahrt über Zeitstempel)
        LocalDateTime ende = start.plusMinutes(20);
        kunde.fahrtBeenden(ende);
        System.out.println("Restguthaben: " + kunde.getBalance() + " €");
        System.out.println("Restakku: " + scooter.getBattery() + "%");

        // 4. Fahrthistorie anzeigen
        System.out.println("\nFahrthistorie von " + kunde.getName() + ":");
        for (Fahrt fahrt : kunde.getFahrtHistorie()) {
            System.out.println("- " + fahrt);
        }

        // 5. Konto aufladen
        System.out.println();
        kunde.kontoAusgleichen(10.0);
    }
}
