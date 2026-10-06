package fbs.lg1;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Startklasse für Aufgabe 22.
 * Zeigt beispielhaft, wie Hersteller, Produkt, Kunde, Bewertung und Antwort
 * zusammenarbeiten.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Hersteller anlegen
        Hersteller hersteller = new Hersteller("TechCorp", "support@techcorp.de");

        // 2. Produkt mit diesem Hersteller anlegen (Preis als BigDecimal)
        Produkt laptop = new Produkt("P-001", "Laptop", new BigDecimal("999.99"), hersteller);

        // 3. Zwei Kunden anlegen
        Kunde manfred = new Kunde("K-001", "Manfred");
        Kunde udo = new Kunde("K-002", "Udo");

        // 4. Bewertungen erstellen
        // Hinweis: Der Konstruktor von Bewertung meldet sich selbst beim Produkt an
        Bewertung bewertung1 = new Bewertung(manfred, laptop, 5, "Super Gerät!", LocalDate.now(), true);
        Bewertung bewertung2 = new Bewertung(udo, laptop, 3, "Ganz okay.", LocalDate.now(), false);

        // 5. Der Hersteller antwortet auf die zweite Bewertung
        bewertung2.setAntwort(new Antwort("Danke für Ihr Feedback!", LocalDate.now()));

        // 6. Ergebnisse ausgeben
        gibProduktAus(laptop);
        gibBewertungAus(bewertung1);
        gibBewertungAus(bewertung2);
    }

    // Gibt die wichtigsten Daten eines Produkts aus
    private static void gibProduktAus(Produkt produkt) {
        System.out.println("Produkt:      " + produkt.getProduktName() + " (" + produkt.getProduktNummer() + ")");
        System.out.println("Preis:        " + produkt.getProduktPreis() + " EUR");
        System.out.println("Hersteller:   " + produkt.getHersteller().getHerstellername());
        System.out.println("Bewertungen:  " + produkt.getBewertungen().size());
        System.out.println("Durchschnitt: " + produkt.berechneDurchschnittssterne() + " Sterne");
        System.out.println();
    }

    // Gibt eine einzelne Bewertung inklusive Antwort (falls vorhanden) aus
    private static void gibBewertungAus(Bewertung bewertung) {
        System.out.println(bewertung.getKunde().getName() + " -> " + bewertung.getSterne() + " Sterne: "
                + bewertung.getKommentar() + (bewertung.hatGekauft() ? " (verifizierter Kauf)" : ""));

        // Antwort nur ausgeben, wenn eine existiert
        if (bewertung.getAntwort() != null) {
            System.out.println("   Antwort: " + bewertung.getAntwort().getAntworttext());
        }
    }
}
