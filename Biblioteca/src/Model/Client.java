package Model;

import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class Client extends User {
    private ArrayList<Book> bookList;
        
    // Constructor:
    public Client(String name, String firstLastName, String secondLastName, ID userID, 
                  int userNumber, Password clientPassword, String lastLogin, ArrayList<Book> bookList) {
        
        super(name, firstLastName, secondLastName, userID, userNumber, clientPassword, lastLogin);
        this.bookList = bookList;
    }
    
    
    // Getters:
    public ArrayList<Book> getBookList() {
        return bookList;
    }
    
    // Setters:
    protected void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
    
    
    // Elimina un libro de la lista de libros:
    public void removeBook(Book book) {
        for(int i = 0; i < getBookList().size(); i++) {
            if(getBookList().get(i).equals(String.valueOf(book.getID().getCharCode()))) {
                getBookList().remove(i);
                break;
            }
        }
    }
    
    
    // Métodos concretos:
    // Verifica que el password del cliente sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if(password.length == 9) {
            return super.getUserPassword().compare(password);
        } 
        return false;
    }

    // Retorna el número de indentificación del cliente:
    @Override
    public String identity() {
        return  "CLNT-" + String.format("%05d", super.getUserNumber()) 
              + "\nID: " + String.valueOf(super.getUserID().getCharCode())  
              + "\nNombre: " + super.getName() 
              + "\nApellidos: " + super.getFirstLastName() + ' ' + super.getSecondLastName() 
              + "\nÚltimo acceso: " + super.getLastLogin()
              + "\nLibros prestados: " + getBookList();
    }
}
