package Model;

import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class BookCollection {
    
    private ArrayList<Book> bookList;
    private String listOfElements;

    public BookCollection(ArrayList<Book> bookList) {
        this.bookList = bookList;
        generateListOfElements();
    }

    // Getters:
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public int getNumberOfBooks() {
        return bookList.size();
    }

    public String getListOfElements() {
        return listOfElements;
    }

    // Setters:
    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public void setListOfElements(String listOfElements) {
        this.listOfElements = listOfElements;
    }
    
    
    // Genera un String con la lista de elementos:
    private void generateListOfElements() {
        String elements = "{";

        for (int i = 0; i < getNumberOfBooks(); i++) {
            // Se añade el (ID,TítuloLibro):
            elements += "(" + String.valueOf(bookList.get(i).getID().getIDCode()) + ',' + bookList.get(i).getTitle() + ')' + ',';
        }
        elements += "}";

        setListOfElements(elements);
    }
    

    // Verifica si hay libros disponibles dentro de la colección:
    public String isAvailable(Book book) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).isBorrowed() == false) {
                return "Colección: "  + getListOfElements();
            }
        }
        System.out.println("No hay libros disponibles actualmente.");
        return null;
    }
}
