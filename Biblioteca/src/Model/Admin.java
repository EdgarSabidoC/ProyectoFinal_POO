package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author edgar
 */
public class Admin extends User {

    public Admin(String name, String firstLastName, String secondLastName, ID userID, 
                 int userNumber , Password adminPassword, String lastLogin) {
        super(name, firstLastName, secondLastName, userID, userNumber, adminPassword, lastLogin);
    }
    
    // Agrega un nuevo usuario:
    // ENTRADA: Un fichero de la base de datos y un Client.
    // SALIDA: Retorna true si se añadió el cliente correctamente, retorna false si no.
    public boolean addClient(File file, Client client, int clientNumber) {
        try
        { 
            // Se prepara el fichero para escribir al final, append = true:
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(file, true));
 
            outputFile.write(client.getUserNumber());  // N°. de cliente.
            outputFile.write("\\|\\|");                // Separador.
            outputFile.write(String.valueOf(client.getUserID()));  // ID
            outputFile.write("\\|\\|");          
            outputFile.write(client.getName());    // Nombre
            outputFile.write("\\|\\|");          
            outputFile.write(client.getFirstLastName());   // Apellido paterno
            outputFile.write("\\|\\|");          
            outputFile.write(client.getSecondLastName()); // Apellido materno
            outputFile.write("\\|\\|");             
            outputFile.write(String.valueOf(client.getPassword()));  // Contraseña
            outputFile.write("\\|\\|");  
            outputFile.write(client.getLastLogin()); // Último acceso
            outputFile.write("\\|\\|");  
            outputFile.write(client.getBookListElements()); // Listado de libros prestados actualmente.              
            outputFile.newLine();                     // Nueva línea.
 
            outputFile.close(); // Se cierra el fichero.
           
            return true; // Se añadió correctamente el archivo.
        }
        catch (IOException fileError)
        {
            System.out.println("Han ocurrido problemas: " + fileError.getMessage());
        }
        return false;
    }
   
    // Elimina un usuario:
    public boolean deleteClient(String client_ID) {
        return false;
    }
    
    
    // Agrega un Book a BookLibrary:
    // ENTRADA: Un fichero de la base de datos y un Book.
    // SALIDA: Retorna true si se añadió el libro correctamente, retorna false si no.
    public boolean addBook(File file, Book book) {
        try
        { 
            // Se prepara el fichero para escribir al final, append = true:
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(file, true));
 
            outputFile.write(String.valueOf(book.getID()));      // ID del libro
            outputFile.write("\\|\\|");          // Separador.
            outputFile.write(book.getAuthor());  // Autor
            outputFile.write("\\|\\|");          
            outputFile.write(book.getYear());    // Año
            outputFile.write("\\|\\|");          
            outputFile.write(book.getTitle());   // Título
            outputFile.write("\\|\\|");          
            outputFile.write(book.getEdition()); // Edición
            outputFile.write("\\|\\|");             
            outputFile.write(book.getEditorial());  // Editorial
            outputFile.write("\\|\\|");             
            outputFile.write(book.getNumPages());   // Número de páginas
            outputFile.write("\\|\\|");             
            outputFile.write(book.getISBN());       // ISBN
            outputFile.write("\\|\\|");             
            outputFile.write(book.getAvailability()); // Disponibilidad
            outputFile.write("\\|\\|");               
            outputFile.newLine();                     // Nueva línea
 
            outputFile.close(); // Se cierra el fichero.
            
            return true; // Se añadió correctamente el archivo.
        }
        catch (IOException fileError)
        {
            System.out.println("Han ocurrido problemas: " + fileError.getMessage());
        }
        return false;
    }
    
    // Elimina un Book de BookLibrary:
    public boolean deleteBook(String book_ID) {
        return false;
    }
    
    // Permite regresar un libro:
    public boolean returnABook(ArrayList<Client> listOfClients, Client client, ArrayList<Book> sourceOfBooks, Book book) {      
        if(book == null || sourceOfBooks.isEmpty() == true || !(sourceOfBooks.contains(book))) {
            return false;
        }
        
        int bookIndex = sourceOfBooks.indexOf(book); // Se obtiene el índice del libro.
        sourceOfBooks.get(bookIndex).setBorrowed(false); // Se cambia diponibilidad del libro por no prestado.
        
        int clientIndex = listOfClients.indexOf(client); // Se obtiene el índice del cliente.
        
        // Se quita el libro de la cuenta del cliente:
        for(int i = 0; i < client.getBookList().size(); i++) {
            if(client.getBookList().get(i).contains(String.valueOf(book.getID().getIDCode()))) {
                client.getBookList().remove(i);
                break;
            }
        }
        
        // Se reemplaza el cliente por el actualizado:
        listOfClients.set(clientIndex, client);
        
        return true;
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
        return "ADMN-" + getUserID();
    }
}
