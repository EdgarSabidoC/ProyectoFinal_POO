package Model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

    // Encripta un objeto y lo escribe en un archivo.
    // ENTRADA: Un objeto Serializable, una clave de tipo SecretKey y el archivo.
    private void objectEncoderToFile(Serializable object, File file) {
        try {
            // Se encripta out1:
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));

            // Se escribe la lista en el archivo binario:
            output.writeObject(object);
            output.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Decodifica un objeto dentro de un archivo y lo retorna.
    // ENTRADA: Archivo File.
    // SALIDA: Objeto Serializable.
    private Serializable getObjectFromFile(File file) {

        // Objeto:
        Serializable object = null;

        try {
            ObjectInputStream output = new ObjectInputStream(new FileInputStream(file));
            
            // Se obtiene el objeto:
            object = (Serializable) output.readObject();
            
            // Se cierra el fichero:
            output.close();

        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        } catch (ClassNotFoundException e3) {
            System.out.println(e3.getMessage());
        } 
          
        return object;
    }

    // Carga la información a un arreglo de tipo Book.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    private boolean loadInfoFromBooksFile() {

        // Se verifica si existe el archivo:
        if (!(getBooksFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Cargando el archivo: " + getBooksFile().getName() + "\n");
            try {
                getBooksFile().createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            // Si existe el archivo.
            // Desencripta y carga la lista de libros:
            setBooksList((ArrayList<Book>) getObjectFromFile(getBooksFile()));
        }
        return false;
    }

    // Carga la información del archivo admins al ArrayList de admins.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    private boolean loadInfoFromAdminsFile() {
        // Se verifica si existe el archivo:
        if (!(getAdminsFile().exists())) {
            // Si no existe el archivo, se crea:
            System.out.println("Creando el archivo: " + getAdminsFile().getName() + "\n");
            try {
                getAdminsFile().createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            // Si existe el archivo.
            // Desencripta y carga la lista de admins:
            setAdminsList((ArrayList<User>) getObjectFromFile(getAdminsFile()));
        }
        return false;
    }

    // Carga la información del archivo clientes al ArrayList de clientes.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    private boolean loadInfoFromClientsFile() {
        // Se verifica si existe el archivo:
        if (!(getClientsFile().exists())) {
            // Si no existe el archivo, se crea:
            System.out.println("Creando el archivo: " + getClientsFile().getName() + "\n");
            try {
                getClientsFile().createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            // Si existe el archivo.
            // Desencripta y carga la lista de clientes:
            setClientsList((ArrayList<User>) getObjectFromFile(getClientsFile()));
            return true;
        }
        return false;
    }

    // Actualiza el archivo de Admins.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    private boolean updateAdminsFile() {
        if (!(getAdminsFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getAdminsFile().getName() + "\n");
        } else {
            objectEncoderToFile(getAdminsList(), getAdminsFile());
            return true;
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Actualiza el archivo de clientes.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    private boolean updateClientsFile() {
        if (!(getClientsFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getClientsFile().getName() + "\n");
        } else {
            objectEncoderToFile(getClientsList(), getClientsFile());
            return true;
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Actualiza un archivo de Book.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    private boolean updateBooksFile() {
        // Se verifica si existe el archivo:
        if (!(getBooksFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getBooksFile().getName() + "\n");
        } else {
            objectEncoderToFile(getBooksList(), getBooksFile());
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Carga la información de los archivos a los ArrayList.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean loadInfoFromFiles() {
        boolean clientsFile, adminsFile, booksFile;
        
        clientsFile = loadInfoFromClientsFile();
        adminsFile = loadInfoFromAdminsFile();
        booksFile = loadInfoFromBooksFile();
        
        if (clientsFile == false || adminsFile == false || booksFile == false) {
            return false;
        }

        return true;
    }

    // Actualiza la información de los archivos desde los ArrayList.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean updateInfoInFiles() {
        if (updateClientsFile() == false || updateAdminsFile() == false || updateBooksFile() == false) {
            return false;
        }
        
        return true;
    }

    // Devuelve un libro:
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean returnABook(Client client, Book book) {

        if (!(getBooksList().contains(book)) || !(getClientsList().contains(client))) {
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

        if (!(getBooksList().contains(book)) || !(getClientsList().contains(client))) {
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
