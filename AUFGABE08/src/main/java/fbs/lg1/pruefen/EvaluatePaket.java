package fbs.lg1.pruefen;

public class EvaluatePaket {
    public String evaluatePaket(double gewicht, boolean masseOk, boolean hatGefahrstoffe, boolean istZerbrechlich) {
        if (!masseOk) {
            return "Masse außerhalb der Norm";
        }
        if (gewicht > 31.5) {
            return "Paket zu schwer";
        }
        if (gewicht <= 0) {
            return "Ungültiges Gewicht";
        }
        if (hatGefahrstoffe) {
            return "Gefahrstoffe nicht erlaubt";
        }
        if (istZerbrechlich) {
            return "Paket angenommen mit Hinweis zerbrechlich";
        }
        return "Paket angenommen";
    }
}
