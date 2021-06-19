package App;

import Model.*;
import Model.Library;
import java.io.File;
import java.util.ArrayList;



/**
 *
 * @author edgar
 */
public class PruebaBiblioteca {
    private static final SuperAdmin root = new SuperAdmin("Root", "Super", "Admin", 0, new Date().getDateAndTime());
    
    public static Library biblioteca;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Book> listaLibros = new ArrayList<>();
        ArrayList<User> listaClientes = new ArrayList<>();
        ArrayList<User> listaAdmins = new ArrayList<>(); 
        listaAdmins.add(root);
        
        File adminsDB = new File("Admins.ldb2");
        File clientsDB = new File("Clients.ldb2");
        File booksDB = new File("Books.ldb2");
        biblioteca = new Library(listaAdmins, listaClientes, listaLibros, adminsDB, clientsDB, booksDB);
        biblioteca.loadInfoFromFiles();

        // MODIFICAR CÓDIGO A PARTIR DE AQUÍ:
        
        for(int i = 0; i < biblioteca.getNumberOfClients(); i++) {
            System.out.println(biblioteca.getClientsList().get(i).identity() + "\n\n");
        }
        
        // Libros:
        /*listaLibros.add(new Book("Jose Maria Garcia Lopez", 2013, "El corazón de la piedra", "Primera Edicion",
            "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, "N/A"));
        listaLibros.add(new Book("Jose Maria Garcia Lopez", 2013, "El corazón de la piedra", "Primera Edicion",
            "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, "N/A"));
        listaLibros.add(new Book("Jose Maria Garcia Lopez", 2013, "El corazón de la piedra", "Primera Edicion",
            "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, "N/A"));
        // Clientes:
        listaClientes.add(new Client("Carlos Antonio", "Ruiz", "Dominguez", new ID("_base64", 10), 1, new Password(9),
            new Date().getDateAndTime(), new ArrayList<>()));
        listaClientes.add(new Client("Alfredo", "Cota", "Armenta", new ID("_base64", 10), 2, new Password(9),
            new Date().getDateAndTime(), new ArrayList<>()));
        
        // Admin root:
        System.out.println(listaAdmins.get(0).identity());
        
        // Admin:
        listaAdmins.add(new Admin("Edgar", "Sabido", "Cortes", new ID("_base64", 10), 1, new Password(12),
                        new Date().getDateAndTime()));
        System.out.println(listaAdmins.get(1).identity());
        
        Client cliente = (Client) listaClientes.get(0);
        
        Book libro1 = listaLibros.get(0);
        Book libro2 = listaLibros.get(1);
        
        System.out.println("\n\nLibros antes de ser prestados: \n");
        System.out.println("\n"+listaLibros.get(0).toString());
        System.out.println("\n"+listaLibros.get(1).toString());
        
                
        // Se regresa el libro:
        biblioteca.returnABook(cliente, libro1);
        biblioteca.returnABook(cliente, libro2);
        System.out.println("\n\nLibros después de ser regresados: \n");
        System.out.println("\n"+listaLibros.get(0).toString());
        System.out.println("\n"+listaLibros.get(1).toString());
        */
        
        // Se prestan dos libros:
         
        Book libro1 = biblioteca.getBooksList().get(0);
        Book libro2 = biblioteca.getBooksList().get(1);
        Client cliente = (Client) biblioteca.getClientsList().get(0);
        biblioteca.bookABook(cliente, libro1);
        biblioteca.bookABook(cliente, libro2);
        System.out.println("\n\nLibros después de ser prestados: \n");
        System.out.println("\n"+ biblioteca.getBooksList().get(0).toString());
        System.out.println("\n"+ biblioteca.getBooksList().get(1).toString());
        
        // NO MODIFICAR (Actualiza la información de los archivos):
        biblioteca.updateInfoInFiles();
        
    }
    
}
