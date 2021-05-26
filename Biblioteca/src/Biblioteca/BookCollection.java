package Biblioteca;

import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class BookCollection {
    
    private String collectionName;
    private ArrayList<Book> bookList;

    public BookCollection(String collectionName, ArrayList<Book> bookList) {
        this.collectionName = collectionName;
        this.bookList = bookList;
    } 
    
    // Getters:
    public String getName() {
        return collectionName;
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }
    
    // Setters:
    public void setName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
    
    
    // Verifica si hay libros disponibles dentro de la colección:
    public String isAvailable(Book book) {
        for(Book element : bookList) {
            if(element.isBorrowed() == false) {
                return "Collection: " + collectionName + "\n" + element.toString();
            }
        }
        System.out.println("No hay libros disponibles actualmente.");
        return null;
    }
}
