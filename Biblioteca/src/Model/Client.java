package Model;

import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class Client extends User {
    private ArrayList<String> bookList;
        
    // Constructor:
    public Client(String name, String firstLastName, String secondLastName, ID userID, 
                  int userNumber, Password clientPassword, String lastLogin, ArrayList<String> bookList) {
        
        super(name, firstLastName, secondLastName, userID, userNumber, clientPassword, lastLogin);
        this.bookList = bookList;
    }
    
    
    // Getters:
    protected ArrayList<String> getBookList() {
        return bookList;
    }
    
    // Retorna un string con todos los elementos de la lista de libros
    // separados por '$'
    protected String getBookListElements() {
        String str = "";
        for(int i = 0; i < getBookList().size(); i++) {
            str += getBookList().get(i)+'$';
        }
        
        return str;
    }
    
    
    // Setters:
    protected void setBookList(ArrayList<String> bookList) {
        this.bookList = bookList;
    }
    
    // Permite apartar un libro:
    public boolean bookABook(ArrayList<Book> sourceOfBooks, Book book) {      
        if(book == null || sourceOfBooks.isEmpty() == true || !(sourceOfBooks.contains(book))) {
            return false;
        }
       
        Book tempBook = sourceOfBooks.get(sourceOfBooks.indexOf(book));
        tempBook.setBorrowed(true); // Se cambia el libro como prestado.
        
        // Se crea la fecha de devolución:
        Date date = new Date();
        
        // Se añade el string a la lista:
        getBookList().add("(" + String.valueOf(tempBook.getID().getIDCode()) + ',' +
                          tempBook.getTitle() + ',' + date +')');
        
        return true;
    }
    
    
    // Métodos concretos:
    // Verifica que el password del cliente sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if(password.length == 9) {
            super.getPassword().compare(password);
        } 
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    // Retorna el número de indentificación del cliente:
    @Override
    public String identity() {
        return "CLNT-" + super.getUserID();
    }
}
