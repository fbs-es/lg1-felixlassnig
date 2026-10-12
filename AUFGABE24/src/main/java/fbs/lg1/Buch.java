package fbs.lg1;

public class Buch {
    // Attribute
    private String isbn;
    private String title;
    private String autor;
    private boolean verfuegbar;

    // Konstruktor
    public Buch(String isbn, String title, String autor) {
        this.isbn = isbn;
        this.title = title;
        this.autor = autor;
        this.verfuegbar = true;
    }

    // Getter & Setter
    public String getISBN() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }
}