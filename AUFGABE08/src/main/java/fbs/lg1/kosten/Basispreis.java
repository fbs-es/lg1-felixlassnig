package fbs.lg1.kosten;

public enum Basispreis {
    S(5),
    M(10),
    L(15);

    private final int preis;

    Basispreis(int preis) {
        this.preis = preis;
    }

    public int getBasispreis() {
        return this.preis;
    }
}