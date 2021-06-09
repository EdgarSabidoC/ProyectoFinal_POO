package Library;

import java.util.UUID;

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
    private UUID code; // Código único del libro.
    
    public Book(String author, int year, String title, String edition, String editorial, int numPages, int ISBN, boolean borrowed) {
        this.author = author;
        this.year = year;
        this.title = title;
        this.edition = edition;
        this.editorial = editorial;
        this.numPages = numPages;
        this.ISBN = ISBN;
        this.borrowed = borrowed;
        generateCode(); // Se genera el código del libro.
    }
    
    // Getters:
    
    
    // Setters:
   
    
    // Genera el código del libro:
    private void generateCode(){
        // Se genera un código único:
        setCode(UUID.randomUUID());
    }
    
    // Verifica que el entero que se ingresa sea igual al código Hash del código del libro,
    // retorna True si son iguales y False si no.
    public boolean isValidCode(int hashCode) {
        return hashCode == getCode().hashCode();
    }
    
    @Override
    public String toString() {
        String availability = "Disponible";
        
        if(this.borrowed == true) {
            availability = "Prestado";
        }
        
        return getAuthor() + ". (" + getYear() + "). " + getTitle() + ". " + getEdition() + ". " + getEditorial() + 
            ". Número de páginas: " + getNumPages() + ". ISBN: " + getISBN() + ". Código: " + getCode() + ". Disponibilidad: " + availability;
    }
}