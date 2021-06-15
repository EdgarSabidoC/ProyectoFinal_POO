package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class Library {

    private ArrayList<Book> booksList;
    private ArrayList<User> clientsList;
    private ArrayList<User> adminsList;
    private File clientsFile;
    private File adminsFile;
    private File booksFile;

    // Constructores:
    public Library() {
    }

    public Library(ArrayList<User> adminsList, ArrayList<User> clientsList,
        ArrayList<Book> booksList, File adminsFile, File clientsFile,
        File booksFile) {
        this.booksList = booksList;
        this.clientsList = clientsList;
        this.adminsList = adminsList;
        this.clientsFile = clientsFile;
        this.adminsFile = adminsFile;
        this.booksFile = booksFile;
    }

    // Getters:
    public File getClientsFile() {
        return clientsFile;
    }

    public File getAdminsFile() {
        return adminsFile;
    }

    public File getBooksFile() {
        return booksFile;
    }

    public ArrayList<Book> getBooksList() {
        return booksList;
    }

    public ArrayList<User> getClientsList() {
        return clientsList;
    }

    public ArrayList<User> getAdminsList() {
        return adminsList;
    }

    public int getNumberOfClients() {
        return getClientsList().size();
    }

    public int getNumberOfAdmins() {
        return getAdminsList().size();
    }

    public int getNumberOfBooks() {
        return getBooksList().size();
    }

    // Setters:
    public void setBooksList(ArrayList<Book> booksList) {
        this.booksList = booksList;
    }

    public void setClientsList(ArrayList<User> clientsList) {
        this.clientsList = clientsList;
    }

    public void setAdminsList(ArrayList<User> adminsList) {
        this.adminsList = adminsList;
    }

    public void setClientsFile(File clientsFile) {
        this.clientsFile = clientsFile;
    }

    public void setAdminsFile(File adminsFile) {
        this.adminsFile = adminsFile;
    }

    public void setBooksFile(File booksFile) {
        this.booksFile = booksFile;
    }

    
    // Devuelve un libro:
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean returnABook(Client client, Book book) {
        
        if(!(getBooksList().contains(book)) || !(getClientsList().contains(client))) {
            // Si en las listas no se encuentra el libro o el cliente:
            return false;
        }
        
        // Se cambia la fecha de devolución a N/A:
         getBooksList().get(getBooksList().indexOf(book)).setReturnDate("N/A");
        
        // Se cambia el estado de prestado del libro:
        getBooksList().get(getBooksList().indexOf(book)).setBorrowed(false);
        
        // Se elimina el libro de la colección del cliente:
        int clientIndex = getClientsList().indexOf(client);
       ((Client) getClientsList().get(clientIndex)).removeBook(book);
        
        return true;
    }
    
    
    // Reserva un libro.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean bookABook(Client client, Book book) {
        
        if(!(getBooksList().contains(book)) || !(getClientsList().contains(client))) {
            // Si en las listas no se encuentra el libro o el cliente:
            return false;
        }
        
        
        // Se agrega la fecha de devolución al libro:
        getBooksList().get(getBooksList().indexOf(book)).setReturnDate(new Date().getDateAfterSevenS());
        
        // Se cambia el estado de prestado del libro:
        getBooksList().get(getBooksList().indexOf(book)).setBorrowed(true);
        
        // Se añade el libro a la colección del cliente:
        ((Client) getClientsList().get(getClientsList().indexOf(client))).getBookList().add(String.valueOf(book.getID().getCharCode()));
        
        return true;
    }
    
    // Carga la información a un arreglo de tipo Book.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean loadInfoFromBooksFile() {

        // Se verifica si existe el archivo:
        if (!(getBooksFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getBooksFile().getName() + "\n");
        } else {
            // Si el archivo existe:
            try {
                // Se lee el archivo:
                BufferedReader inputFile = new BufferedReader(new FileReader(getBooksFile()));
                String line; // Variable temporal para almacenar la línea del archivo.

                // Si es un archivo de libros:
                if (getBooksFile().getName().contains("Books") == true) {

                    // Se recorre todo el archivo línea por línea:
                    while ((line = inputFile.readLine()) != null) {

                        // Se generan los tokens de los atributos del libro:
                        String[] split = line.split("\\|\\|"); // Utiliza || como separador.                        

                        // Se quitan los espacios al inicio y al final de los tokens (por si hay) con String.trim()
                        // También se hace parsing para cambiar el tipo de dato al del atributo correspondiente del cliente.
                        char[] arrayID = (split[0].trim()).toCharArray();
                        String author = split[1].trim();
                        int year = Integer.parseInt(split[2].trim());
                        String title = split[3].trim();
                        String edition = split[4].trim();
                        String editorial = split[5].trim();
                        int numPages = Integer.parseInt(split[6].trim());
                        String ISBN = split[7].trim();
                        String availability = split[8].trim();
                        String returnDate = split[9].trim();
                        
                        // Se configura la disponibilidad del libro:
                        boolean borrowed = true;
                        if (availability.equals("Disponible") == true) {
                            borrowed = false;
                        }

                        // Se crea el ID del libro:
                        ID ID = new ID();
                        ID.setID(arrayID);

                        // Se ingresa el libro a la lista de libros:
                        getBooksList().add(new Book(author, year, title, edition, editorial, numPages, ISBN, ID, borrowed, returnDate));
                    }
                }
                inputFile.close(); // Se cierra el archivo.
                return true;
            } catch (IOException fileError) {
                // Si hubo un error al leer el archivo:
                System.out.println("Error: " + fileError.getMessage());
            }
        }
        return false;
    }

    // Carga la información del archivo admins al ArrayList de admins.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean loadInfoFromAdminsFile() {
        // Se verifica si existe el archivo:
        if (!(getAdminsFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getAdminsFile().getName() + "\n");
        } else {
            try {
                // Se lee el archivo:
                BufferedReader inputFile = new BufferedReader(new FileReader(getAdminsFile()));
                String line; // Variable temporal para almacenar la línea del archivo.

                // Se recorre todo el archivo línea por línea:
                while ((line = inputFile.readLine()) != null) {

                    // Se generan los tokens de los atributos del admin o superadmin:
                    String[] split = line.split("\\|\\|"); // Utiliza || como separador.                        

                    // Se quitan los espacios al inicio y al final de los tokens (por si hay) con String.trim()
                    // También se hace parsing para cambiar el tipo de dato al del atributo correspondiente del libro.
                    int num = Integer.parseInt(split[0].trim());
                    char[] arrayID = (split[1].trim()).toCharArray();
                    String name = split[2].trim();
                    String lastname1 = split[3].trim();
                    String lastname2 = split[4].trim();
                    char[] arrayPassword = split[5].trim().toCharArray();
                    String lastLogin = split[6].trim();

                    // Se crea la contraseña:
                    Password password = new Password();
                    password.setPassword(arrayPassword);

                    // Se crea el ID:
                    ID ID = new ID();
                    ID.setID(arrayID);

                    // Se ingresa el Admin a la lista de usuarios.
                    if (num == 0) {
                        getAdminsList().add(new SuperAdmin(name, lastname1, lastname2, ID, num, password, lastLogin)); // Es SuperAdmin (root).
                    } else {
                        getAdminsList().add(new Admin(name, lastname1, lastname2, ID, num, password, lastLogin));
                    }
                }
                inputFile.close(); // Se cierra el archivo.
                return true;
            } catch (IOException fileError) {
                // Si hubo un error al leer el archivo:
                System.out.println("Error: " + fileError.getMessage());
            }
        }
        return false;
    }
    
    // Carga la información del archivo clientes al ArrayList de clientes.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean loadInfoFromClientsFile() {

        // Se verifica si existe el archivo:
        if (!(getClientsFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getClientsFile().getName() + "\n");
        } else {

            // Si el archivo existe:
            try {
                // Se lee el archivo:
                BufferedReader inputFile = new BufferedReader(new FileReader(getClientsFile()));
                String line; // Variable temporal para almacenar la línea del archivo.

                // Se recorre todo el archivo línea por línea:
                while ((line = inputFile.readLine()) != null) {

                    // Se generan los tokens de los atributos del cliente:
                    String[] split = line.split("\\|\\|"); // Utiliza || como separador.                        

                    // Se quitan los espacios al inicio y al final de los tokens (por si hay) con String.trim()
                    // También se hace parsing para cambiar el tipo de dato al del atributo correspondiente del cliente.
                    int num = Integer.parseInt(split[0].trim());
                    char[] arrayID = (split[1].trim()).toCharArray();
                    String name = split[2].trim();
                    String lastname1 = split[3].trim();
                    String lastname2 = split[4].trim();
                    char[] arrayPassword = split[5].trim().toCharArray();
                    String lastLogin = split[6].trim();
                    String books = split[7].trim();

                    // Se crea la contraseña:
                    Password password = new Password();
                    password.setPassword(arrayPassword);

                    // Se crea el ID:
                    ID ID = new ID();
                    ID.setID(arrayID);

                    // Se crea la lista de libros:
                    String[] splitBooks = books.split("$");
                    ArrayList<String> listOfBooks = new ArrayList<>();

                    for (int i = 0; i < splitBooks.length; i++) {
                        listOfBooks.add(splitBooks[i]);
                    }

                    // Se ingresa el cliente a la lista de usuarios:
                    getClientsList().add(new Client(name, lastname1, lastname2, ID, num, password, lastLogin, listOfBooks));
                }
                inputFile.close(); // Se cierra el archivo.
                return true;
            } catch (IOException fileError) {
                // Si hubo un error al leer el archivo:
                System.out.println("Error: " + fileError.getMessage());
            }
        }
        return false;
    }

    // Actualiza el archivo de Admins.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean updateAdminsFile() {
        if (!(getAdminsFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getAdminsFile().getName() + "\n");
        } else {
            try {
                // Se prepara el fichero para ser sobreescrito:
                BufferedWriter outputFile = new BufferedWriter(new FileWriter(getAdminsFile()));

                for (int i = 0; i < getNumberOfAdmins(); i++) {

                    outputFile.write(getAdminsList().get(i).getUserNumber());  // N°. de admin.
                    outputFile.write("\\|\\|");                // Separador.
                    outputFile.write(String.valueOf(getAdminsList().get(i).getUserID()));  // ID
                    outputFile.write("\\|\\|");
                    outputFile.write(getAdminsList().get(i).getName());    // Nombre
                    outputFile.write("\\|\\|");
                    outputFile.write(getAdminsList().get(i).getFirstLastName());   // Apellido paterno
                    outputFile.write("\\|\\|");
                    outputFile.write(getAdminsList().get(i).getSecondLastName()); // Apellido materno
                    outputFile.write("\\|\\|");
                    outputFile.write(String.valueOf(getAdminsList().get(i).getUserPassword()));  // Contraseña
                    outputFile.write("\\|\\|");
                    outputFile.write(getAdminsList().get(i).getLastLogin()); // Último acceso           
                    outputFile.newLine();  // Nueva línea.
                }
                outputFile.close(); // Se cierra el fichero
                return true;
            } catch (IOException fileError) {
                System.out.println("Han ocurrido problemas al actualizar el archivo de usuarios: " + fileError.getMessage());
            }
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Actualiza el archivo de clientes.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean updateClientsFile() {
        if (!(getClientsFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getClientsFile().getName() + "\n");
        } else {
            try {

                // Se prepara el fichero para ser sobreescrito:
                BufferedWriter outputFile = new BufferedWriter(new FileWriter(getClientsFile()));

                for (int i = 0; i < getNumberOfClients(); i++) {

                    outputFile.write(getClientsList().get(i).getUserNumber());  // N°. de cliente.
                    outputFile.write("\\|\\|");                // Separador.
                    outputFile.write(String.valueOf(getClientsList().get(i).getUserID()));  // ID
                    outputFile.write("\\|\\|");
                    outputFile.write(getClientsList().get(i).getName());    // Nombre
                    outputFile.write("\\|\\|");
                    outputFile.write(getClientsList().get(i).getFirstLastName());   // Apellido paterno
                    outputFile.write("\\|\\|");
                    outputFile.write(getClientsList().get(i).getSecondLastName()); // Apellido materno
                    outputFile.write("\\|\\|");
                    outputFile.write(String.valueOf(getClientsList().get(i).getUserPassword()));  // Contraseña
                    outputFile.write("\\|\\|");
                    outputFile.write(getClientsList().get(i).getLastLogin()); // Último acceso
                    outputFile.write("\\|\\|");
                    outputFile.write(((Client) getClientsList().get(i)).getBookListElements()); // Listado de libros prestados actualmente.              
                    outputFile.newLine();  // Nueva línea.
                }
                outputFile.close(); // Se cierra el fichero
                return true;
            } catch (IOException fileError) {
                System.out.println("Han ocurrido problemas al actualizar el archivo de usuarios: " + fileError.getMessage());
            }
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Actualiza un archivo de Book.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean updateBooksFile() {
        // Se verifica si existe el archivo:
        if (!(getBooksFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getBooksFile().getName() + "\n");
        } else {
            // Si el archivo existe:
            try {
                // Se prepara el fichero para ser sobreescrito:
                BufferedWriter outputFile = new BufferedWriter(new FileWriter(getBooksFile()));

                for (int i = 0; i < getBooksList().size(); i++) {
                    outputFile.write(String.valueOf(getBooksList().get(i).getID()));  // ID
                    outputFile.write("\\|\\|");                                    // Separador.
                    outputFile.write(getBooksList().get(i).getAuthor()); // Autor
                    outputFile.write("\\|\\|");
                    outputFile.write(getBooksList().get(i).getYear()); // Año
                    outputFile.write("\\|\\|");
                    outputFile.write(getBooksList().get(i).getTitle()); // Título
                    outputFile.write("\\|\\|");
                    outputFile.write(getBooksList().get(i).getEdition()); // Edición
                    outputFile.write("\\|\\|");
                    outputFile.write(getBooksList().get(i).getEditorial()); // Editorial.
                    outputFile.write("\\|\\|");
                    outputFile.write(getBooksList().get(i).getNumPages()); // N°. de páginas.
                    outputFile.write("\\|\\|");
                    outputFile.write(getBooksList().get(i).getISBN()); // ISBN
                    outputFile.write("\\|\\|");
                    outputFile.write(getBooksList().get(i).getAvailability()); // Disponibilidad
                    outputFile.write("\\|\\|");
                    outputFile.newLine();  // Nueva línea.
                }
                outputFile.close(); // Se cierra el fichero.
            } catch (IOException fileError) {
                // Si hubo un error al leer el archivo:
                System.out.println("Error: " + fileError.getMessage());
            }
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Busca una cadena dentro de una lista de libros y retorna una lista con los libros que coincidan
    // en Autor, ISBN o Título con la cadena ingresada, si no hay ninguno, retorna una lista vacía:
    public ArrayList<Book> searchBook(String word) {
        word = word.trim(); // Se eliminan los espacios al principio y al final de la palabra.

        // Se crea la lista de libros vacía:
        ArrayList<Book> listOfBooks = new ArrayList<>();

        // Se recorre la lista original de libros:
        for (int i = 0; i < getBooksList().size(); i++) {
            if (getBooksList().get(i).getAuthor().equals(word) == true) {
                // Coinciden en autor:
                listOfBooks.add(getBooksList().get(i));
            } else if (getBooksList().get(i).getISBN().equals(word)) {
                // Coinciden en ISBN:
                listOfBooks.add(getBooksList().get(i));
            } else if (getBooksList().get(i).getTitle().equals(word) == true) {
                // Coinciden en título:
                listOfBooks.add(getBooksList().get(i));
            }
        }

        // Se retorna la lista de libros:
        return listOfBooks;
    }
}
