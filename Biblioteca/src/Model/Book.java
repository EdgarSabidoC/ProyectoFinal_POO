package Model;

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
    private char[] ID; // ID único del libro.
    
    // Constructor:
    public Book(String author, int year, String title, String edition, String editorial, int numPages, int ISBN, char[] ID, boolean borrowed) {
        this.author = author;
        this.year = year;
        this.title = title;
        this.edition = edition;
        this.editorial = editorial;
        this.numPages = numPages;
        this.ISBN = ISBN;
        this.ID = ID;
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

    public char[] getID() {
        return ID;
    }
    
    public String getAvailability() {
        String availability = "Disponible";
        
        if(isBorrowed() == true) {
            availability = "No disponible";
        }
        
        return availability;
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

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void setCode(char[] ID) {
        this.ID = ID;
    }
    
    public void setAvailability(String availability) {
        if(availability.equals("No disponible")) {
            setBorrowed(true);
        } else if(availability.equals("Disponible")) {
            setBorrowed(false);
        }
    }
    
    // Verifica que el entero que se ingresa sea igual al código del libro,
    // retorna True si son iguales y False si no.
    public boolean isValidCode(char[] ID) {
        return getID().equals(String.valueOf(ID));
    }
    
    @Override
    public String toString() {
        return "ID: " + String.valueOf(getID()) + 
               "\nAutor: "+ getAuthor() + 
               "\nAño: " + getYear() + 
               "\nTítulo: " + getTitle() +
               "\nEdición: " + getEdition() + 
               "\nEditorial: " + getEditorial() +
               "\nPag: " + getNumPages() +
               "\nISBN: " + getISBN() +
               "\nDisponibilidad: " + getAvailability();
    }
}