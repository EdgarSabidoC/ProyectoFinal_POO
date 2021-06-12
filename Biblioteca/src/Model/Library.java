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

    private File clientsFile;
    private File adminsFile;
    private File booksFile;
    private int numberOfClients;
    private int numberOfAdmins;
    private int numberOfBooks;

    // Constructores:
    public Library() {
    }

    public Library(File clientsFile, File adminsFile, File booksFile, int numberOfClients,
        int numberOfAdmins, int numberOfBooks) {

        this.clientsFile = clientsFile;
        this.adminsFile = adminsFile;
        this.booksFile = booksFile;
        this.numberOfClients = numberOfClients;
        this.numberOfAdmins = numberOfAdmins;
        this.numberOfBooks = numberOfBooks;
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

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public int getNumberOfAdmins() {
        return numberOfAdmins;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    // Setters:
    public void setClientsFile(File clientsFile) {
        this.clientsFile = clientsFile;
    }

    public void setAdminsFile(File adminsFile) {
        this.adminsFile = adminsFile;
    }

    public void setBooksFile(File booksFile) {
        this.booksFile = booksFile;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void setNumberOfAdmins(int numberOfAdmins) {
        this.numberOfAdmins = numberOfAdmins;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    
    // Carga la información a un arreglo de tipo Book.
    // ENTRADA: Archivo que contiene la información de un User.
    // SALIDA: ArrayList de tipo Book con la información correspondiente.
    public ArrayList<Book> loadInfoFromBooksFile(File file) {
        ArrayList<Book> listOfBooks = new ArrayList<>();

        // Se verifica si existe el archivo:
        if (!(file.exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + file.getName() + "\n");
        } else {
            // Si el archivo existe:
            try {
                // Se lee el archivo:
                BufferedReader inputFile = new BufferedReader(new FileReader(file));
                String line; // Variable temporal para almacenar la línea del archivo.

                // Si es un archivo de libros:
                if (file.getName().contains("Books") == true) {

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
                        int ISBN = Integer.parseInt(split[7].trim());
                        String availability = split[8].trim();
                        
                        // Se configura la disponibilidad del libro:
                        boolean borrowed = true;                         
                        if(availability.equals("Disponible") == true) {
                            borrowed = false;
                        } 
                        
                        // Se crea el ID del libro:
                        ID ID = new ID();
                        ID.setID(arrayID);

                        // Se ingresa el libro a la lista de libros:
                        listOfBooks.add(new Book(author, year, title, edition, editorial, numPages, ISBN, ID, borrowed));
                    } 
                }
                inputFile.close(); // Se cierra el archivo.
            } catch (IOException fileError) {
                // Si hubo un error al leer el archivo:
                System.out.println("Error: " + fileError.getMessage());
            }
        }
        return listOfBooks;
    }

    // Carga la información a un arreglo de tipo User (Client o Admin).
    // ENTRADA: Archivo que contiene la información de un User.
    // SALIDA: ArrayList de tipo User con la información correspondiente.
    public ArrayList<User> loadInfoFromAUsersFile(File file) {

        ArrayList<User> listOfUsers = new ArrayList<>();

        // Se verifica si existe el archivo:
        if (!(file.exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + file.getName() + "\n");
        } else {

            // Si el archivo existe:
            try {
                // Se lee el archivo:
                BufferedReader inputFile = new BufferedReader(new FileReader(file));
                String line; // Variable temporal para almacenar la línea del archivo.

                // Si es un archivo de clientes:
                if (file.getName().contains("Clients") == true) {

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
                        
                        for(int i = 0; i < splitBooks.length; i++) {
                            listOfBooks.add(splitBooks[i]);
                        }
                        
                        // Se ingresa el cliente a la lista de usuarios:
                        listOfUsers.add(new Client(name, lastname1, lastname2, ID, num, password, lastLogin, listOfBooks));
                    }
                } // Si es un archivo de admins:
                else if (file.getName().contains("Admins") == true) {
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
                            listOfUsers.add(new SuperAdmin(name, lastname1, lastname2, ID, num, password, lastLogin)); // Es SuperAdmin (root).
                        } else {
                            listOfUsers.add(new Admin(name, lastname1, lastname2, ID, num, password, lastLogin));
                        }
                    }
                }
                inputFile.close(); // Se cierra el archivo.
            } catch (IOException fileError) {
                // Si hubo un error al leer el archivo:
                System.out.println("Error: " + fileError.getMessage());
            }
        }
        // Se retorna la lista de clientes (vacía si no contiene ningún cliente):
        return listOfUsers;
    }

    // Actualiza el archivo de algún tipo de usuario (Client o Admin):
    // ENTRADA: Un arreglo con la información ya actualizada.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean updateUsersFile(ArrayList<User> listOfUsers) {
        try {
            // Si es la lista de Clientes:
            if (listOfUsers.get(0) instanceof Client) {

                // Se prepara el fichero para ser sobreescrito:
                BufferedWriter outputFile = new BufferedWriter(new FileWriter(getClientsFile()));

                setNumberOfClients(listOfUsers.size()); // Se actualiza el número de clientes.

                for (int i = 0; i < listOfUsers.size(); i++) {

                    outputFile.write(listOfUsers.get(i).getUserNumber());  // N°. de cliente.
                    outputFile.write("\\|\\|");                // Separador.
                    outputFile.write(String.valueOf(listOfUsers.get(i).getUserID()));  // ID
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfUsers.get(i).getName());    // Nombre
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfUsers.get(i).getFirstLastName());   // Apellido paterno
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfUsers.get(i).getSecondLastName()); // Apellido materno
                    outputFile.write("\\|\\|");
                    outputFile.write(String.valueOf(listOfUsers.get(i).getPassword()));  // Contraseña
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfUsers.get(i).getLastLogin()); // Último acceso
                    outputFile.write("\\|\\|");
                    outputFile.write(((Client) listOfUsers.get(i)).getBookListElements()); // Listado de libros prestados actualmente.              
                    outputFile.newLine();  // Nueva línea.
                }
                outputFile.close(); // Se cierra el fichero.
            } // Si es la lista de Admins:
            else if (listOfUsers.get(0) instanceof Admin) {

                // Se prepara el fichero para ser sobreescrito:
                BufferedWriter outputFile = new BufferedWriter(new FileWriter(getAdminsFile()));

                setNumberOfAdmins(listOfUsers.size()); // Se actualiza el número de admins.

                for (int i = 0; i < listOfUsers.size(); i++) {

                    outputFile.write(listOfUsers.get(i).getUserNumber());  // N°. de admin.
                    outputFile.write("\\|\\|");                // Separador.
                    outputFile.write(String.valueOf(listOfUsers.get(i).getUserID()));  // ID
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfUsers.get(i).getName());    // Nombre
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfUsers.get(i).getFirstLastName());   // Apellido paterno
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfUsers.get(i).getSecondLastName()); // Apellido materno
                    outputFile.write("\\|\\|");
                    outputFile.write(String.valueOf(listOfUsers.get(i).getPassword()));  // Contraseña
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfUsers.get(i).getLastLogin()); // Último acceso           
                    outputFile.newLine();  // Nueva línea.
                }
                outputFile.close(); // Se cierra el fichero.
            }
            return true; // Se actualizó correctamente el archivo.
        } catch (IOException fileError) {
            System.out.println("Han ocurrido problemas al actualizar el archivo de usuarios: " + fileError.getMessage());
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Actualiza un archivo de Book:
    // ENTRADA: Un arreglo con la información ya actualizada.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean updateBooksFile(ArrayList<Book> listOfBooks) {
        // Se verifica si existe el archivo:
        if (!(getBooksFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getBooksFile().getName() + "\n");
        } else {
            // Si el archivo existe:
            try {
                // Se prepara el fichero para ser sobreescrito:
                BufferedWriter outputFile = new BufferedWriter(new FileWriter(getBooksFile()));

                setNumberOfBooks(listOfBooks.size()); // Se actualiza el número de admins.

                for (int i = 0; i < listOfBooks.size(); i++) {
                    outputFile.write(String.valueOf(listOfBooks.get(i).getID()));  // ID
                    outputFile.write("\\|\\|");                                    // Separador.
                    outputFile.write(listOfBooks.get(i).getAuthor()); // Autor
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfBooks.get(i).getYear()); // Año
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfBooks.get(i).getTitle()); // Título
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfBooks.get(i).getEdition()); // Edición
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfBooks.get(i).getEditorial()); // Editorial.
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfBooks.get(i).getNumPages()); // N°. de páginas.
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfBooks.get(i).getISBN()); // ISBN
                    outputFile.write("\\|\\|");
                    outputFile.write(listOfBooks.get(i).getAvailability()); // Disponibilidad
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
    public ArrayList<Book> searchBook(ArrayList<Book> sourceOfBooks,String word) {
        word.trim(); // Se eliminan los espacios al principio y al final de la palabra.
        
        // Se crea la lista de libros vacía:
        ArrayList<Book> listOfBooks = new ArrayList<>();
        
        // Se recorre la lista original de libros:
        for(int i = 0; i < sourceOfBooks.size(); i++) {
            if(sourceOfBooks.get(i).getAuthor().equals(word) == true) {
                // Coinciden en autor:
                listOfBooks.add(sourceOfBooks.get(i));
            } else if(sourceOfBooks.get(i).getISBN() == Integer.parseInt(word)) {
                // Coinciden en ISBN:
                listOfBooks.add(sourceOfBooks.get(i));
            } else if(sourceOfBooks.get(i).getTitle().equals(word) == true) {
                // Coinciden en título:
                listOfBooks.add(sourceOfBooks.get(i));
            }
        }
        
        // Se retorna la lista de libros:
        return listOfBooks;
    }
}
