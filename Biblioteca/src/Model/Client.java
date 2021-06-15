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
    public ArrayList<String> getBookList() {
        return bookList;
    }
    
    // Retorna un string con todos los elementos de la lista de libros
    // separados por '$'
    public String getBookListElements() {
        int i;
        String str = "";
        for(i = 0; i < getBookList().size()-1; i ++) {
            str += getBookList().get(i)+ "$";
        }
        str += getBookList().get(i);
        
        return str;
    }
    
    
    // Setters:
    protected void setBookList(ArrayList<String> bookList) {
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
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    // Retorna el número de indentificación del cliente:
    @Override
    public String identity() {
        return "CLNT-" + String.valueOf(super.getUserID().getCharCode()) + 
               "\nElments: " + getBookList();
    }
}
