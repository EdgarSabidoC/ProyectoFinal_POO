package Model;

import java.util.ArrayList;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class Member extends User {
    private ArrayList<Book> bookList;
        
    // Constructor:
    public Member(String name, String firstLastName, String secondLastName, ID userID, 
                  int userNumber, Password memberPassword, Date lastLogin, ArrayList<Book> bookList) {
        
        super(name, firstLastName, secondLastName, userID, userNumber, memberPassword, lastLogin);
        this.bookList = bookList;
    }
    
    
    // Getters:
    public ArrayList<Book> getBookList() {
        return bookList;
    }
    
    public String getBooksID() {
        
        // Si la lista es null o está vacía:
        if(getBookList() == null || getBookList().isEmpty()){
            return "[]";
        }
        
        StringBuilder str = new StringBuilder();
        str.append('[');
        int i;

        for(i = 0; i < getBookList().size()-1; i++) {
            str.append(String.valueOf(getBookList().get(i).getID().getCharCode()));
            str.append(',');
        }
        str.append(String.valueOf(getBookList().get(i).getID().getCharCode()));
        str.append(']');
        return str.toString();
    }
    
    // Setters:
    protected void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
    
    // Reserva un libro.
    // ENTRADA: Lista de libros y libro.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean bookABook(ArrayList<Book> booksList, Book book) {

        if (!(booksList.contains(book))) {
            // Si en las listas no se encuentra el libro:
            return false;
        }

        // Se agrega la fecha de devolución al libro:
        booksList.get(booksList.indexOf(book)).setReturnDate(new Date());

        // Se cambia el estado de prestado del libro:
        booksList.get(booksList.indexOf(book)).setBorrowed(true);

        // Se añade el libro a la colección del miembro:
        getBookList().add(book);

        return true;
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
    
    // Verifica que el password del miembro sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if(password.length == 9) {
            return super.getUserPassword().compare(password);
        } 
        return false;
    }

    // Retorna la indentificación del miembro:
    @Override
    public String identity() {
        StringBuilder str = new StringBuilder();
        
        str.append("CLNT-");
        str.append(String.format("%07d", super.getUserNumber()));
        str.append("\nID: ");
        str.append(String.valueOf(super.getUserID().getCharCode()));  
        str.append("\nNombre: ");
        str.append(super.getName());
        str.append("\nApellidos: ");
        str.append(super.getFirstLastName());
        str.append(' ');
        str.append(super.getSecondLastName());
        str.append("\nÚltimo acceso: ");
        str.append(super.getLastLogin().getDateAndTime());
        str.append("\nLibros prestados: ");
        str.append(getBooksID());
        
        return str.toString();
    }
}
