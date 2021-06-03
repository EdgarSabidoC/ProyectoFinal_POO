package User;

import Library.BookCollection;
import java.util.UUID;

/**
 *
 * @author edgar
 */
public class Client extends User {
    private UUID clientID;
    private Password clientPassword;
    private BookCollection bookList;

    public Client(String name, String firstLastName, String secondLastName, BookCollection bookList) {
        super(name, firstLastName, secondLastName);
        generatePassword(); // Se genera el password de 10 chars.
        this.bookList = bookList;
    }
    
    // Getter:
    public BookCollection getBookList() {
        return bookList;
    }
    
    private UUID getClientID() {
        return clientID;
    }
    
    // Setter:
    public void setBookList(BookCollection bookList) {
        this.bookList = bookList;
    }
    
    // Genera el password:
    private void generatePassword() {
        this.clientPassword = new Password(10);
    }
    
    // Métodos concretos:
    // Verifica que el password del cliente sea correcto:
    @Override
    public boolean authenticate(Password password) {
        if(password.getLength() == 10) {
            return clientPassword.compare(password);
        } 
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    // Retorna el número de indentificación del cliente:
    @Override
    public String identity() {
        return "CLNT-" + getClientID().hashCode();
    }
}
