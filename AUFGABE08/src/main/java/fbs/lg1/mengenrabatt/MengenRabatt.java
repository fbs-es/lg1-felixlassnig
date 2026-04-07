package fbs.lg1.mengenrabatt;

public class MengenRabatt {

    public enum Stufe {
        Mini(0),
        Small(5),
        Medium(10),
        Large(20);

        private final int prozent;

        Stufe(int prozent) {
            this.prozent = prozent;
        }

        public int getProzent() {
            return prozent;
        }
    }

    public static int berechneRabattProzent(int anzahlPakete) {
        if (anzahlPakete <= 10) {
            return Stufe.Mini.getProzent();
        }
        if (anzahlPakete <= 50) {
            return Stufe.Small.getProzent();
        }
        if (anzahlPakete <= 100) {
            return Stufe.Medium.getProzent();
        }
        return Stufe.Large.getProzent();
    }

    public static double berechnePreisNachRabatt(double preisProPaket, int anzahlPakete) {
        if (anzahlPakete <= 0 || preisProPaket < 0) {
            throw new IllegalArgumentException("Anzahl und Preis müssen >= 0 sein");
        }

        double gesamt = preisProPaket * anzahlPakete;
        int rabattProzent = berechneRabattProzent(anzahlPakete);
        return gesamt * (100 - rabattProzent) / 100.0;
    }

}
