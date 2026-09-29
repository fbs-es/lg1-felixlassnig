package fbs.lg1;

public class Main {
    public static void main(String[] args) {
        // 1. Kunde und Roller erstellen
        Kunde kunde = new Kunde(1, "Max Mustermann", "max@schule.at", 15.0);
        Scooter scooter = new Scooter("SC-001");

        // 2. Roller ausleihen
        kunde.rollerAusleihen(scooter);
        System.out.println("Roller ausgeliehen. Entsperrt: " + !scooter.isLocked());

        // 3. Fahrt durchführen (20 Minuten) und beenden
        kunde.fahrtBeenden(20);
        System.out.println("Restguthaben: " + kunde.getBalance() + " €");
        System.out.println("Restakku: " + scooter.getBattery() + "%");

        // 4. Konto aufladen
        kunde.kontoAusgleichen(10.0);
    }
}
