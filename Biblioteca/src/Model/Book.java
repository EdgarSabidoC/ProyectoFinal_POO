package Model;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class Book implements Serializable {
    
    private String author;
    private int year;
    private String title;
    private String edition;
    private String editorial;
    private int numPages;
    private String ISBN;
    private boolean borrowed;    
    private ID ID;
    private Date returnDate;
    
    // Constructor:
    public Book(String author, int year, String title, String edition, String editorial, 
                int numPages, String ISBN, ID ID, boolean borrowed, Date returnDate) {
        this.author = author;
        this.year = year;
        this.title = title;
        this.edition = edition;
        this.editorial = editorial;
        this.numPages = numPages;
        this.ISBN = ISBN;
        this.ID = ID;
        this.borrowed = borrowed;
        this.returnDate = returnDate;
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

    public String getISBN() {
        return ISBN;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public ID getID() {
        return ID;
    }
    
    public String getAvailability() {
        String availability = "Disponible";
        
        if(isBorrowed() == true) {
            availability = "No disponible";
        }
        
        return availability;
    }

    public Date getReturnDate() {
        return returnDate;
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

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void setCode(ID ID) {
        this.ID = ID;
    }
    
    public void setAvailability(String availability) {
        if(availability.equals("No disponible")) {
            setBorrowed(true);
        } else if(availability.equals("Disponible")) {
            setBorrowed(false);
        }
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setID(ID ID) {
        this.ID = ID;
    }
    
    // Verifica que el entero que se ingresa sea igual al código del libro,
    // retorna True si son iguales y False si no.
    public boolean isValidCode(char[] ID) {
        return Arrays.equals(getID().getCharCode(), ID);
    }
    
    
    @Override
    public String toString() {
        String date;
        if(getReturnDate() == null) {
            date = "N/A";
        } else {
            date = getReturnDate().getDateAfterSevenS();
        }    
        
        StringBuilder str = new StringBuilder();
        
        str.append("ID: "); 
        str.append(String.valueOf(getID().getCharCode()));
        str.append("\nAutor: ");
        str.append(getAuthor());
        str.append("\nAño: ");
        str.append(getYear());
        str.append("\nTítulo: ");
        str.append(getTitle());
        str.append("\nEdición: ");
        str.append(getEdition()); 
        str.append("\nEditorial: ");
        str.append(getEditorial());
        str.append("\nPág: ");
        str.append(getNumPages());
        str.append("\nISBN: ");
        str.append(getISBN());
        str.append("\nDisponibilidad: ");
        str.append(getAvailability());
        str.append("\nFecha de devolución: ");
        str.append(date);
        
        return str.toString();
    }
}