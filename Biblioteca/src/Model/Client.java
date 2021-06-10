package Model;

/**
 *
 * @author edgar
 */
public class Client extends User {
    private BookCollection bookList;
    
    // Constructor:
    public Client(String name, String firstLastName, String secondLastName, ID user_ID, 
                  int userNumber, Password clientPassword, Date last_login, BookCollection bookList) {
        
        super(name, firstLastName, secondLastName, user_ID, userNumber, clientPassword, last_login);
        this.bookList = bookList;
    }
    
    
    // Getters:
    protected BookCollection getBookList() {
        return bookList;
    }

    
    // Setters:
    protected void setBookList(BookCollection bookList) {
        this.bookList = bookList;
    }
    
    
    // Métodos concretos:
    // Verifica que el password del cliente sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if(password.length == 9) {
            return getPassword().compare(password);
        } 
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    // Retorna el número de indentificación del cliente:
    @Override
    public String identity() {
        return "CLNT-" + super.getUser_ID();
    }
}
