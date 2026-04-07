package fbs.lg1.kosten;

public class Paketannahme {

    public EnumAbnahme annahmePruefen(boolean gefahrenstoff, boolean zuSchwer, boolean zuGross, boolean zerbrechlich) {
        if (gefahrenstoff) {
            return EnumAbnahme.ABLEHNEN;
        }
        if (zuSchwer || zuGross) {
            return EnumAbnahme.SPERRGUT;
        }
        if (zerbrechlich) {
            return EnumAbnahme.ANNAHME_ZERBRECHLICH;
        }
        return EnumAbnahme.ANNAHME;
    }
}