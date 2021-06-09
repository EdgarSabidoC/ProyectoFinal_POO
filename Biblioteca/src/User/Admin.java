package User;

import Library.Book;
import Library.Magazine;

/**
 *
 * @author edgar
 */
public class Admin extends User {

    public Admin(String name, String firstLastName, String secondLastName, ID user_ID, 
                 int userNumber , Password adminPassword, Date last_login) {
        super(name, firstLastName, secondLastName, user_ID, userNumber, adminPassword, last_login);
    }
    
    // Agrega un nuevo usuario:
    public boolean addClient(Client client) {
        return false;
    }
    
    // Elimina un usuario:
    public boolean deleteClient(String client_ID) {
        return false;
    }
    
    
    // Agrega un Book a BookLibrary:
    public boolean addBook(Book book) {
        return false;
    }
    
    // Elimina un Book de BookLibrary:
    public boolean deleteBook(String book_ID) {
        return false;
    }
    
    // Agrega un Magazine a MagazineLibrary:
    public boolean addMagazine(Magazine magazine) {
        return false;
    }
    
    // Elimina un Magazine de MagazineLibrary:
    public boolean deleteMagazine(String magazine_ID) {
        return false;
    }
    
    
    // Métodos concretos:    
    // Verifica que el password del Admin sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if(password.length == 12) {
            return super.getPassword().compare(password);
        } 
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    // Retorna la indentificación del admin:
    @Override
    public String identity() {
        return "ADMN-" + super.getUser_ID();
    }
}
