package Library;

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
    public String getCollectionName() {
        return collectionName;
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }
    
    // Setters:
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
    
    
    // Verifica si hay libros disponibles dentro de la colección:
    public String isAvailable(Book book) {
        for(Book element : bookList) {
            if(element.isBorrowed() == false) {
                return "Collection: " + getCollectionName() + "\n" + element.toString();
            }
        }
        System.out.println("No hay libros disponibles actualmente.");
        return null;
    }
}
