package fbs.lg1;

import java.time.Duration;
import java.time.LocalDateTime;

public class Fahrt {

    // Attribute für eine Fahrt
    private final Scooter scooter;
    private final LocalDateTime startZeit;
    private final LocalDateTime endZeit;
    private final double kosten;

    public Fahrt(Scooter scooter, LocalDateTime startZeit, LocalDateTime endZeit, double kosten) {
        this.scooter = scooter;
        this.startZeit = startZeit;
        this.endZeit = endZeit;
        this.kosten = kosten;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public LocalDateTime getStartZeit() {
        return startZeit;
    }

    public LocalDateTime getEndZeit() {
        return endZeit;
    }

    public double getKosten() {
        return kosten;
    }

    // Berechnet wie viele Minuten die Fahrt gedauert hat
    public long getDauerInMinuten() {
        if (startZeit == null || endZeit == null) {
            return 0;
        }
        return Duration.between(startZeit, endZeit).toMinutes();
    }

    @Override
    public String toString() {
        return "Fahrt{" +
                "scooter=" + (scooter != null ? scooter.getScooterId() : "null") +
                ", startZeit=" + startZeit +
                ", endZeit=" + endZeit +
                ", dauer=" + getDauerInMinuten() + " Min." +
                ", kosten=" + String.format(java.util.Locale.US, "%.2f", kosten) + " €" +
                '}';
    }
}
