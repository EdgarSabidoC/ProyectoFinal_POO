package App;

import Model.*;
import Model.Library;
import java.io.File;
import java.util.ArrayList;



/**
 *
 * @author edgar sabido cortes
 */
public class PruebaBiblioteca {
    // Ya se cargó previamente el SuperAdmin (Root):
    //private static final SuperAdmin root = new SuperAdmin("Root", "Super", "Admin", 0, new Date().getDateAndTime());
    public static Library biblioteca;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // NO MODIFICAR LO QUE ESTÁ AQUÍ:

        //ArrayList<User> listaAdmins = new ArrayList<>(); 
        //listaAdmins.add(root);
        
        File adminsDB = new File("Admins.ldb2");
        File clientsDB = new File("Clients.ldb2");
        File booksDB = new File("Books.ldb2");
        biblioteca = new Library(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), adminsDB, clientsDB, booksDB);
        biblioteca.loadInfoFromFiles();
        SuperAdmin root = (SuperAdmin) biblioteca.getAdminsList().get(0);
            
        // MODIFICAR CÓDIGO A PARTIR DE AQUÍ:
        
        // Estos ya están en los archivos:
        /*// Libros:
        root.addBookToList(biblioteca.getBooksList(), new Book("José María García López", 2013, "El corazón de la piedra", "Primera Edición",
                                                               "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, "N/A"));
        
        root.addBookToList(biblioteca.getBooksList(), new Book("José María García López", 2013, "El corazón de la piedra", "Primera Edición",
                                                               "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, "N/A"));
        
        root.addBookToList(biblioteca.getBooksList(), new Book("José María García López", 2013, "El corazón de la piedra", "Primera Edición",
                                                               "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, "N/A"));
        
        // Clientes:
        root.addUserToList(biblioteca.getClientsList(), new Client("Carlos Antonio", "Ruíz", "Domínguez", new ID("_base64", 10), 1, new Password(9),
                                        new Date().getDateAndTime(), new ArrayList<>()));
        root.addUserToList(biblioteca.getClientsList(), new Client("Alfredo", "Cota", "Armenta", new ID("_base64", 10), 2, new Password(9),
            new Date().getDateAndTime(), new ArrayList<>()));
        
        // Admin root:
        System.out.println(listaAdmins.get(0).identity());
        
        // Admin:
        root.addUserToList(biblioteca.getAdminsList(), new Admin("Edgar", "Sabido", "Cortes", new ID("_base64", 10), 
                                                  1, new Password(12), new Date().getDateAndTime()));
        */
        
        Client cliente = (Client) biblioteca.getClientsList().get(0);
        Book libro1 = biblioteca.getBooksList().get(0);
        
        System.out.println("\n\nLibro antes de ser prestado: \n");
        System.out.println("\n"+ biblioteca.getBooksList().get(0).toString());

        // Se presta un libro:
        biblioteca.bookABook(cliente, libro1);
        System.out.println("\n\nLibro después de ser prestado: \n");
        System.out.println("\n"+ biblioteca.getBooksList().get(0).toString());
        
        // Se regresa el libro:
        biblioteca.returnABook(cliente, libro1);
        System.out.println("\n\nLibro después de ser regresado: \n");
        System.out.println("\n"+biblioteca.getBooksList().get(0).toString());
        
        // Se busca un cliente por su nombre:
        System.out.println("\n\n\nCLIENTES: ");
        String[] clientData1 = {"Carlos Antonio", "Ruíz", "Domínguez"};
        ArrayList<User> listC1 = root.searchUserInList(biblioteca.getClientsList(), clientData1);
        for(User user : listC1) {
            System.out.println("\n" + ((Client)user).identity());
        }
        
        // Se busca un cliente por su número de usuario:
        String[] clientData2 = {"2"};
        ArrayList<User> listC2 = root.searchUserInList(biblioteca.getClientsList(), clientData2);
        for(User user : listC2) {
            System.out.println("\n" + ((Client)user).identity());
        }
            
        // Se busca un admin por su ID de usuario:
        System.out.println("\n\n\nADMIN:\n");
        String[] adminData = {String.valueOf(biblioteca.getAdminsList().get(1).getUserID().getCharCode())};
        ArrayList<User> listA = root.searchUserInList(biblioteca.getAdminsList(), adminData);
        
        for(User user : listA) {
            System.out.println(((Admin)user).identity());
        }
        
        
        // Se buscan e imprimen todos los libros a través del ISBN:
        System.out.println("\n\n\nLIBROS");
        ArrayList<Book> listaB = biblioteca.searchBook("978-84-939750-7-4");
        
        for(Book book : listaB) {
            System.out.println("\n" + book.toString());
        }

        // NO MODIFICAR (Actualiza la información de los archivos):
        biblioteca.updateInfoInFiles();
        
    }
    
}
