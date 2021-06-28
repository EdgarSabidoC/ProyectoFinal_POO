package Model;

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
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class Library {

    private ArrayList<Book> booksList;
    private ArrayList<User> membersList;
    private ArrayList<User> adminsList;
    private File membersFile;
    private File adminsFile;
    private File booksFile;

    // Constructores:
    public Library() {
    }

    public Library(ArrayList<User> adminsList, ArrayList<User> membersList,
        ArrayList<Book> booksList, File adminsFile, File membersFile,
        File booksFile) {
        this.booksList = booksList;
        this.membersList = membersList;
        this.adminsList = adminsList;
        this.membersFile = membersFile;
        this.adminsFile = adminsFile;
        this.booksFile = booksFile;
    }

    // Getters:
    public File getMembersFile() {
        return membersFile;
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

    public ArrayList<User> getMembersList() {
        return membersList;
    }

    public ArrayList<User> getAdminsList() {
        return adminsList;
    }

    public int getNumberOfMembers() {
        return getMembersList().size();
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

    public void setMembersList(ArrayList<User> membersList) {
        this.membersList = membersList;
    }

    public void setAdminsList(ArrayList<User> adminsList) {
        this.adminsList = adminsList;
    }

    public void setMembersFile(File membersFile) {
        this.membersFile = membersFile;
    }

    public void setAdminsFile(File adminsFile) {
        this.adminsFile = adminsFile;
    }

    public void setBooksFile(File booksFile) {
        this.booksFile = booksFile;
    }

    // Escribe un objeto en un archivo.
    // ENTRADA: Un objeto Serializable y el archivo.
    private void objectToFile(Serializable object, File file) {
        try {
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
    // ENTRADA: Un archivo.
    // SALIDA: Objeto Serializable.
    private Serializable getObjectFromFile(File file) {

        // Objeto:
        Serializable object = null;

        try {
            ObjectInputStream output = new ObjectInputStream(new FileInputStream(file));

            // Se obtiene el objeto:
            object = (Serializable) output.readObject();

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
            System.out.println("Creando el archivo: " + getBooksFile().getName() + "...\n");
            try {
                getBooksFile().createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            // Si existe el archivo.
            // Carga la lista de libros:
            System.out.println("Cargando el archivo: " + getBooksFile().getName() + "...\n");
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
            System.out.println("Creando el archivo: " + getAdminsFile().getName() + "...\n");
            try {
                
                // Se crea el nuevo archivo:
                getAdminsFile().createNewFile();
                
                // Se crea el usuario root:  
                ArrayList<User> adminList = new ArrayList<>();
                adminList.add(new SuperAdmin("Root", "Super", "Admin", new Date()));
                setAdminsList(adminList); // Se ingresa el usuario root a la lista.
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            // Si existe el archivo.
            // Carga la lista de admins:
            System.out.println("Cargando el archivo: " + getAdminsFile().getName() + "...\n");
            setAdminsList((ArrayList<User>) getObjectFromFile(getAdminsFile()));
        }
        return false;
    }

    // Carga la información del archivo miembros al ArrayList de miembros.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    private boolean loadInfoFromMembersFile() {
        // Se verifica si existe el archivo:
        if (!(getMembersFile().exists())) {
            // Si no existe el archivo, se crea:
            System.out.println("Creando el archivo: " + getMembersFile().getName() + "...\n");
            try {
                // Se crea el archivo nuevo:
                getMembersFile().createNewFile();
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            // Si existe el archivo.
            // Carga la lista de miembros:
            System.out.println("Cargando el archivo: " + getMembersFile().getName() + "...\n");
            setMembersList((ArrayList<User>) getObjectFromFile(getMembersFile()));
            
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
            objectToFile(getAdminsList(), getAdminsFile());
            return true;
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Actualiza el archivo de Members.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    private boolean updateMembersFile() {
        if (!(getMembersFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getMembersFile().getName() + "\n");
        } else {
            objectToFile(getMembersList(), getMembersFile());
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
            objectToFile(getBooksList(), getBooksFile());
        }
        return false; // No se pudo actualizar el archivo.
    }

    // Carga la información de los archivos a los ArrayList.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean loadInfoFromFiles() {
        boolean membersFile, adminsFile, booksFile;

        membersFile = loadInfoFromMembersFile();
        adminsFile = loadInfoFromAdminsFile();
        booksFile = loadInfoFromBooksFile();

        if (membersFile == false || adminsFile == false || booksFile == false) {
            return false;
        }

        return true;
    }

    // Actualiza la información de los archivos desde los ArrayList.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean updateInfoInFiles() {
        if (updateMembersFile() == false || updateAdminsFile() == false || updateBooksFile() == false) {
            return false;
        }

        return true;
    }

    // Busca una cadena dentro de una lista de libros y retorna una lista con los libros que coincidan
    // en Autor, ISBN o Título con la cadena ingresada, si no hay ninguno, retorna una lista vacía:
    public ArrayList<Book> searchBook(String... data) {

        // Se crea la lista de libros vacía:
        ArrayList<Book> listOfBooks = new ArrayList<>(); // Lista de coincidencias.

        // Se recorre la lista original de libros:
        for (int i = 0; i < getBooksList().size(); i++) {
            // Se recorren los Strings ingresados:
            for (int j = 0; j < data.length; j++) {
                if (getBooksList().get(i).getAuthor().contains(data[j]) == true) {
                    // Coincide alguna palabra con el nombre del autor:
                    listOfBooks.add(getBooksList().get(i)); // Se añade a la lista de coincidencias.
                    break;
                } else if (getBooksList().get(i).getISBN().equals(data[j])) {
                    // Coincide en ISBN:
                    listOfBooks.add(getBooksList().get(i)); // Se añade a la lista de coincidencias.
                    break;
                } else if (getBooksList().get(i).getTitle().contains(data[j]) == true) {
                    // Coincide alguna palabra con el título:
                    listOfBooks.add(getBooksList().get(i)); // Se añade a la lista de coincidencias.
                    break;
                } else if(getBooksList().get(i).getID().compareID(data[j].toCharArray())){
                    // Coincide el ID del libro:
                    listOfBooks.add(getBooksList().get(i)); // Se añade a la lista de coincidencias.
                    break;
                }
            }
        }

        return listOfBooks;
    }
}
