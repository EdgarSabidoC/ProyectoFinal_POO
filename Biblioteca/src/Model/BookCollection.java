package Model;

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
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).isBorrowed() == false) {
                return "Colección: " + getCollectionName() + "\n" + bookList.get(i).toString();
            }
        }
        System.out.println("No hay libros disponibles actualmente.");
        return null;
    }
}
