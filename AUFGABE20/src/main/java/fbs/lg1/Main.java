package fbs.lg1;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        // Zwei Konten anlegen
        Sparkonto konto1 = new Sparkonto("Max Mustermann", new BigDecimal("1000.00"), "AT123456789012345678", "12345678");
        Sparkonto konto2 = new Sparkonto("Erika Musterfrau", new BigDecimal("500.00"), "AT987654321098765432", "87654321");

        System.out.println("Konto 1 Inhaber: " + konto1.getKontoInhaber() + " | Stand: " + konto1.getKontoStand() + " EUR");
        System.out.println("Konto 2 Inhaber: " + konto2.getKontoInhaber() + " | Stand: " + konto2.getKontoStand() + " EUR");

        // 1. Einzahlung
        System.out.println("\n--- 1. Einzahlung auf Konto 1 (250.00 EUR) ---");
        konto1.einzahlen(new BigDecimal("250.00"));
        System.out.println("Neuer Stand Konto 1: " + konto1.getKontoStand() + " EUR");

        // 2. Auszahlung
        System.out.println("\n--- 2. Auszahlung von Konto 1 (150.00 EUR) ---");
        konto1.auszahlen(new BigDecimal("150.00"));
        System.out.println("Neuer Stand Konto 1: " + konto1.getKontoStand() + " EUR");

        // 3. Überweisung
        System.out.println("\n--- 3. Überweisung von Konto 1 an Konto 2 (300.00 EUR) ---");
        boolean transferErfolg = konto1.ueberweisen(konto2, new BigDecimal("300.00"));
        System.out.println("Überweisung erfolgreich: " + transferErfolg);
        System.out.println("Neuer Stand Konto 1: " + konto1.getKontoStand() + " EUR");
        System.out.println("Neuer Stand Konto 2: " + konto2.getKontoStand() + " EUR");

        // 4. Fehlgeschlagene Auszahlung
        System.out.println("\n--- 4. Versuch: Konto 1 um 2000.00 EUR überziehen ---");
        konto1.auszahlen(new BigDecimal("2000.00"));
        System.out.println("Stand Konto 1 nach Abweisung: " + konto1.getKontoStand() + " EUR");
    }
}
