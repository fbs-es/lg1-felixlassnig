package fbs.lg1;

import java.time.LocalDate;

/**
 * Hauptprogramm zur Demonstration der Mitarbeiterverwaltung.
 * Beispielszenario fuer Lassnig IT Solutions mit regionalem Bezug zu Kaernten.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   Mitarbeiterverwaltung - Lassnig IT Solutions Klagenfurt      ");
        System.out.println("   Kontakt: felix.lassnig@kaernten.at | www.lassnig-it.at       ");
        System.out.println("=================================================================\n");

        // 1. Mitarbeiterverwaltung initialisieren
        Mitarbeiterverwaltung verwaltung = new Mitarbeiterverwaltung("Lassnig IT Solutions Klagenfurt am Wörthersee");
        System.out.println("Unternehmen: " + verwaltung.getFirmenname() + "\n");

        // 2. Fest angestellten Mitarbeiter anlegen (Felix Lassnig)
        Angestellter felix = new Angestellter("Felix", "Lassnig", 3800.0, 2021, 30);
        verwaltung.mitarbeiterHinzufuegen(felix);
        System.out.println("1. Angestellter erfasst:");
        System.out.println("   Name: " + felix.getVollerName());
        System.out.println("   Grundgehalt: " + String.format("%.2f", felix.getGehalt()) + " EUR");
        System.out.println("   Einstellungsjahr: " + felix.getEinstellungsjahr() + " (Dienstjahre: " + felix.berechneDienstjahre(2026) + ")");
        System.out.println("   Urlaubsanspruch: " + felix.getUrlaubsanspruch() + " Tage\n");

        // 3. Auszubildende anlegen (Anna Wurzer aus Villach)
        Auszubildender anna = new Auszubildender("Anna", "Wurzer", 1150.0, "Applikationsentwicklung - Coding", 2);
        verwaltung.mitarbeiterHinzufuegen(anna);
        System.out.println("2. Auszubildende erfasst:");
        System.out.println("   Name: " + anna.getVollerName());
        System.out.println("   Lehrlingsentschaedigung: " + String.format("%.2f", anna.getGehalt()) + " EUR");
        System.out.println("   Lehrberuf: " + anna.getAusbildungsberuf());
        System.out.println("   Aktuelles Ausbildungsjahr: " + anna.getAktuellesAusbildungsjahr() + ". Lehrjahr\n");

        // 4. Aushilfskraft anlegen (Lukas Kofler aus St. Veit an der Glan)
        Aushilfe lukas = new Aushilfe("Lukas", "Kofler", 0.0, 16.50);
        verwaltung.mitarbeiterHinzufuegen(lukas);
        System.out.println("3. Aushilfe erfasst:");
        System.out.println("   Name: " + lukas.getVollerName());
        System.out.println("   Stundenlohn: " + String.format("%.2f", lukas.getStundenlohn()) + " EUR/h");
        System.out.println("   Startgehalt: " + String.format("%.2f", lukas.getGehalt()) + " EUR\n");

        // 5. Arbeitszeiten fuer Aushilfe dokumentieren
        System.out.println("--- Arbeitszeiten fuer Lukas Kofler dokumentieren ---");
        lukas.arbeitszeitHinzufuegen(new Arbeitszeit(LocalDate.of(2026, 10, 5), 180)); // 3 Stunden
        lukas.arbeitszeitHinzufuegen(new Arbeitszeit(LocalDate.of(2026, 10, 8), 240)); // 4 Stunden
        lukas.arbeitszeitHinzufuegen(new Arbeitszeit(LocalDate.of(2026, 10, 12), 120)); // 2 Stunden

        for (Arbeitszeit az : lukas.getArbeitszeiten()) {
            System.out.println("   - " + az);
        }
        System.out.println("   Offene Arbeitsstunden gesamt: " + lukas.getOffeneArbeitsstunden() + " Stunden\n");

        // 6. Uebersicht vor Gehaltsanpassung
        System.out.println("--- Gesamtstatus vor Gehaltsanpassung ---");
        System.out.println("Anzahl Beschaeftigte: " + verwaltung.getAnzahlMitarbeiter());
        System.out.println("Gesamte Gehaltssumme: " + String.format("%.2f", verwaltung.berechneGesamtgehalt()) + " EUR\n");

        // 7. Gehaltsanpassungen durchfuehren (kategoriespezifisch)
        System.out.println("--- Kategoriespezifische Gehaltsanpassung durchfuehren ---");

        // Angestellter: +5% Erhoehung
        double altesGehaltFelix = felix.getGehalt();
        felix.passeGehaltAn(5.0);
        System.out.println("1. Angestellter (" + felix.getVollerName() + "): 5% Gehaltserhoehung");
        System.out.println("   Vorher: " + String.format("%.2f", altesGehaltFelix) + " EUR -> Nachher: " + String.format("%.2f", felix.getGehalt()) + " EUR");

        // Auszubildende: Aufstieg ins 3. Lehrjahr mit +150 EUR Erhoehung
        double altesGehaltAnna = anna.getGehalt();
        anna.rueckeInNaechstesLehrjahr(150.0);
        System.out.println("2. Auszubildende (" + anna.getVollerName() + "): Aufstieg ins 3. Lehrjahr (+150 EUR)");
        System.out.println("   Vorher: " + String.format("%.2f", altesGehaltAnna) + " EUR -> Nachher: " + String.format("%.2f", anna.getGehalt()) + " EUR");

        // Aushilfe: Abrechnung aller offenen Arbeitszeiten
        double auszahlungLukas = lukas.rechneOffeneArbeitszeitenAb();
        System.out.println("3. Aushilfe (" + lukas.getVollerName() + "): Abrechnung der geleisteten Stunden");
        System.out.println("   Ausgezahlter Betrag: " + String.format("%.2f", auszahlungLukas) + " EUR");
        System.out.println("   Aktuelles Gehalt: " + String.format("%.2f", lukas.getGehalt()) + " EUR");
        System.out.println("   Verbleibende offene Minuten: " + lukas.getOffeneArbeitsminuten() + "\n");

        // 8. Abschlussuebersicht
        System.out.println("=================================================================");
        System.out.println("   Abschlussuebersicht nach Gehaltsanpassungen                   ");
        System.out.println("=================================================================");
        for (Mitarbeiter m : verwaltung.getMitarbeiterListe()) {
            System.out.println(String.format("   [%-14s] %-18s : %8.2f EUR", m.getKategorie(), m.getVollerName(), m.getGehalt()));
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("   Neue Gesamtgehaltssumme:       " + String.format("%.2f", verwaltung.berechneGesamtgehalt()) + " EUR");
        System.out.println("   Durchschnittsgehalt:            " + String.format("%.2f", verwaltung.berechneDurchschnittsgehalt()) + " EUR");
        System.out.println("=================================================================");
    }
}
