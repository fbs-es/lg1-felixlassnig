package fbs.lg1.kosten;

public enum EnumAbnahme {
    ABLEHNEN("Es handelt sich um einen Gefahrenstoff."),
    SPERRGUT("Es handelt sich um ein Sperrgut."),
    ANNAHME_ZERBRECHLICH("Das Paket ist zerbrechlich."),
    ANNAHME("Es ist ein normales Paket.");

    public final String beschreibung;

    EnumAbnahme(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}