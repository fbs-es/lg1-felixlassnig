package fbs.lg1;

public class Buch {
    // Attribute
    private String isbn;
    private String title;
    private String autor;
    private boolean verfuegbar;

    // Konstruktor von Buch
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

    // Hier wird der Title geholt
    public String getTitle() {
        return title;
    }

    // Hier wird der Auto aufgerufen
    public String getAutor() {
        return autor;
    }

    // Hier wird geprüft ob das Buch verfügbar ist
    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    // Hier wird das Buch auf Verfügbar gesetzt
    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }
}