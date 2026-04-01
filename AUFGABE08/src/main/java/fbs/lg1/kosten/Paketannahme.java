package fbs.lg1.kosten;

public class Paketannahme {

    public EnumAbnahme annahmePruefen(boolean gefahrenstoff, boolean zuSchwer, boolean zuGross, boolean zerbrechlich) {

        EnumAbnahme result = EnumAbnahme.ANNAHME;

        if (gefahrenstoff) {
            result = EnumAbnahme.ABLEHNEN;

        } else if (zuSchwer || zuGross) {
            result = EnumAbnahme.SPERRGUT;

        }
        if ((result == EnumAbnahme.SPERRGUT && zerbrechlich)
                || (result != EnumAbnahme.ABLEHNEN && zerbrechlich)) {

            result = EnumAbnahme.ANNAHME_ZERBRECHLICH;
        }

        return result;
    }
}