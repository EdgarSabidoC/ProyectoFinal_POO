package Biblioteca;

/**
 *
 * @author edgar
 */
public class Book {
    
    private String author;
    private int year;
    private String title;
    private String edition;
    private String editorial;
    private int numPages;
    private int ISBN;
    private boolean borrowed;    

    public Book(String author, int year, String title, String edition, String editorial, int numPages, int ISBN, boolean borrowed) {
        this.author = author;
        this.year = year;
        this.title = title;
        this.edition = edition;
        this.editorial = editorial;
        this.numPages = numPages;
        this.ISBN = ISBN;
        this.borrowed = borrowed;
    }
    
    // Getters:
    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getEdition() {
        return edition;
    }

    public String getEditorial() {
        return editorial;
    }
    
    public int getNumPages() {
        return numPages;
    }
    
    public int getISBN() {
        return ISBN;
    }

    public boolean isBorrowed() {
        return borrowed;
    }
    
    // Setters:
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }
    
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setIsBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        String availability = "Disponible";
        
        if(this.borrowed == true) {
            availability = "Prestado";
        }
        
        return getAuthor() + ". (" + getYear() + "). " + getTitle() + ". " + getEdition() + ". " + getEditorial() + 
            ". Número de páginas: " + getNumPages() + ". ISBN: " + getISBN() + ". Disponibilidad: " + availability;
    }
}