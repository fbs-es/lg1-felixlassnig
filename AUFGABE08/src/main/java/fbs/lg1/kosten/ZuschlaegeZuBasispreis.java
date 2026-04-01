package fbs.lg1.kosten;

public enum ZuschlaegeZuBasispreis {

    INLANDNORMAL(0),
    INDLANDEXPRESS(10),
    AUSLANDNORMAL(10),
    AUSLANDEXPRESS(25);

    private int zuschlag;

    ZuschlaegeZuBasispreis(int zuschlag) {
        this.zuschlag = zuschlag;
    }

    public int getZuschlag() {
        return this.zuschlag;
    }
}