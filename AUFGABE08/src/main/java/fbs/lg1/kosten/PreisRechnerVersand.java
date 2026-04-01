package fbs.lg1.kosten;

public class PreisRechnerVersand {

    public int versandRechner(boolean inland, boolean normalVersand, int basispreis) {
        return basispreis + switch ((inland ? 2 : 0) + (normalVersand ? 1 : 0)) {
            case 3 -> ZuschlaegeZuBasispreis.INLANDNORMAL.getZuschlag();
            case 2 -> ZuschlaegeZuBasispreis.INDLANDEXPRESS.getZuschlag();
            case 1 -> ZuschlaegeZuBasispreis.AUSLANDNORMAL.getZuschlag();
            case 0 -> ZuschlaegeZuBasispreis.AUSLANDEXPRESS.getZuschlag();
            default -> -1;
        };
    }
}