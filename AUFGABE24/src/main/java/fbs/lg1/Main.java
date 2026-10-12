package fbs.lg1;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1. Neue Bibliothek erstellen
        Bibliothek bibliothek = new Bibliothek("Stadtbibliothek Klagenfurt", "Theatergasse 4, 9020 Klagenfurt am Wörthersee");
        System.out.println("Willkommen in der " + bibliothek.getName());

        // 2. Nutzer registrieren
        Nutzer nutzer = new Nutzer(1, "Flassnig", "flassnig@kaernten.at");
        bibliothek.nutzerRegistrieren(nutzer);
        System.out.println("Neuer Nutzer registriert: " + nutzer.getName() + " (" + nutzer.getEmail() + ")");

        // 3. Neues Buch hinzufuegen
        Buch buch = new Buch("978-3-7084-0601-5", "Sagen und Mythen aus Kärnten", "Christine Lavant");
        bibliothek.neuesBuchHinzufuegen(buch);
        System.out.println("Buch hinzugefuegt: " + buch.getTitle() + " - Verfuegbar? " + buch.isVerfuegbar());

        // 4. Buch fuer den Nutzer ausleihen (heutiges Datum)
        LocalDate ausleihdatum = LocalDate.now();
        Ausleihe ausleihe = bibliothek.buchAusleihen(buch, nutzer, ausleihdatum);

        if (ausleihe != null) {
            System.out.println("\n--- Ausleihe erfolgreich ---");
            System.out.println("Ausgeliehenes Buch: " + ausleihe.getBuch().getTitle());
            System.out.println("Ausleiher: " + ausleihe.getNutzer().getName());
            System.out.println("Ausleihdatum: " + ausleihe.getAusleihdatum());
            System.out.println("Geplantes Rueckgabedatum: " + ausleihe.getGeplantesRueckgabedatum());
            System.out.println("Status: " + ausleihe.getStatus());
            System.out.println("Ist Buch noch verfuegbar? " + buch.isVerfuegbar());
        }

        // 5. Buch verlaengern testen
        System.out.println("\n--- Verlängerung testen ---");
        ausleihe.verlaengern();
        System.out.println("Neues Rueckgabedatum nach Verlaengerung: " + ausleihe.getGeplantesRueckgabedatum());

        // 6. Buch zurueckgeben (z. B. nach der Frist, um Verspaetungsstrafe zu pruefen)
        System.out.println("\n--- Rueckgabe testen ---");
        // Wir simulieren ein Rueckgabedatum, das 3 Tage nach der Frist liegt
        LocalDate verspaeteteRueckgabe = ausleihe.getGeplantesRueckgabedatum().plusDays(3);
        ausleihe.rueckgabe(verspaeteteRueckgabe);

        System.out.println("Rueckgabedatum: " + verspaeteteRueckgabe);
        System.out.println("Neuer Status: " + ausleihe.getStatus());
        System.out.println("Verspaetungsstrafe: " + ausleihe.getVerspaetungsStrafe() + " Euro");
        System.out.println("Ist Buch wieder verfuegbar? " + buch.isVerfuegbar());
    }
}
