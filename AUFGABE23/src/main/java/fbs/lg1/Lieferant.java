package fbs.lg1;

import java.util.ArrayList;
import java.util.List;

public class Lieferant {
    private int lieferantnummer;
    private String name;
    // Liste der Produkte die der Lieferant anbietet
    private List<Produkt> produkte;

    // Erstellt einen neuen Lieferanten
    public Lieferant(int lieferantnummer, String name) {
        this.lieferantnummer = lieferantnummer;
        this.name = name;
        this.produkte = new ArrayList<>();
    }

    // Gibt die Lieferantennummer zurück
    public int getLieferantnummer() {
        return lieferantnummer;
    }

    // Gibt den Namen des Lieferanten zurück
    public String getName() {
        return name;
    }

    // Gibt alle Produkte des Lieferanten zurück
    public List<Produkt> getProdukte() {
        return produkte;
    }

    // Weist dem Lieferanten ein neues Produkt zu
    public void addProdukt(Produkt produkt) {
        this.produkte.add(produkt);
    }
}
