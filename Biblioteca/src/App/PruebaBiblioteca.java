package App;

import Model.*;
import Model.Library;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class PruebaBiblioteca {
    
    public static Library biblioteca;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // NO MODIFICAR LO QUE ESTÁ AQUÍ:
        
        File adminsDB = new File("Admins.ldb2");
        File membersDB = new File("Members.ldb2");
        File booksDB = new File("Books.ldb2");
        biblioteca = new Library(new ArrayList(), new ArrayList(), new ArrayList<>(), adminsDB, membersDB, booksDB);
        biblioteca.loadInfoFromFiles();
        
        // Ya se cargó previamente el SuperAdmin (Root):
        SuperAdmin root = (SuperAdmin) biblioteca.getAdminsList().get(0);
        
        
        
        // MODIFICAR CÓDIGO A PARTIR DE AQUÍ:
        
        // Estos ya están en los archivos:
        // Libros:
        /*root.addBookToList(biblioteca.getBooksList(), new Book("José María García López", 2013, "El corazón de la piedra", "Primera Edición",
                                                               "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, null));
        
        root.addBookToList(biblioteca.getBooksList(), new Book("José María García López", 2013, "El corazón de la piedra", "Primera Edición",
                                                               "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, null));
        
        root.addBookToList(biblioteca.getBooksList(), new Book("José María García López", 2013, "El corazón de la piedra", "Primera Edición",
                                                               "Nocturna", 560, "978-84-939750-7-4", new ID("_base64", 10), false, null));
      
        root.addBookToList(biblioteca.getBooksList(), new Book("Carlos Fuentes", 1996, "Aura", "Primera Edición",
                                                               "Alianza Editorial SA", 62, "978-84-206462-6-8", new ID("_base64", 10), false, null));
        
        root.addBookToList(biblioteca.getBooksList(), new Book("Carlos Fuentes", 1996, "Aura", "Primera Edición",
                                                               "Alianza Editorial SA", 62, "978-84-206462-6-8", new ID("_base64", 10), false, null));
        
        
        // Miembros:
        root.addUserToList(biblioteca.getMembersList(), new Member("Carlos Antonio", "Ruíz", "Domínguez", new ID("_base64", 10), new Password(9),
                                        new Date(), new ArrayList<>()));
        root.addUserToList(biblioteca.getMembersList(), new Member("Alfredo", "Cota", "Armenta", new ID("_base64", 10), new Password(9),
            new Date(), new ArrayList<>()));
        
        // Admin:
        root.addUserToList(biblioteca.getAdminsList(), new Admin("Edgar", "Sabido", "Cortes", new ID("_base64", 10), 
                                                       new Password(12), new Date()));
        */
        
        
        Admin admin = (Admin) biblioteca.getAdminsList().get(1);
        Member miembro = (Member) biblioteca.getMembersList().get(0);
        Book libro1 = biblioteca.getBooksList().get(0);
        
        System.out.println("\n\nLibro antes de ser prestado: \n");
        System.out.println("\n"+ biblioteca.getBooksList().get(0).toString());

        // Se presta un libro:
        miembro.bookABook(biblioteca.getBooksList(), libro1);
        System.out.println("\n\nLibro después de ser prestado: \n");
        System.out.println("\n"+ biblioteca.getBooksList().get(0).toString());
        System.out.println("\n\nCLIENTE: ");
        System.out.println(biblioteca.getMembersList().get(0).identity());
        
        // Se regresa el libro:
        admin.returnABook(biblioteca.getMembersList(), biblioteca.getBooksList(), miembro, libro1);
        System.out.println("\n\nLibro después de ser regresado: \n");
        System.out.println("\n"+biblioteca.getBooksList().get(0).toString());
        System.out.println("\n\nCLIENTE: ");
        System.out.println(biblioteca.getMembersList().get(0).identity());
        
        
        // Se busca un miembro por su nombre:
        System.out.println("\n\n\nCLIENTES: ");
        String[] memberData1 = {"Carlos Antonio"};
        ArrayList<User> listC1 = root.searchUserInList(biblioteca.getMembersList(), memberData1);
        for(User user : listC1) {
            System.out.println("\n" + ((Member)user).identity());
        }
        
        // Se busca un miembro por su ID:
        String memberData2 = "21cEvEQO3v";
        ArrayList<User> listC2 = root.searchUserInList(biblioteca.getMembersList(), memberData2);
        for(User user : listC2) {
            System.out.println("\n" + ((Member)user).identity());
        }
            
        // Se busca un admin por su ID de usuario:
        System.out.println("\n\n\nADMIN:\n");
        String adminData = String.valueOf(biblioteca.getAdminsList().get(1).getUserID().getCharCode());
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
        
        System.out.println("\n\n\nAdmins:\n");
        
        // Se imprimen todos los admins:
        for(User user : biblioteca.getAdminsList()) {
            if(user instanceof Admin && !(user instanceof SuperAdmin)) {
                System.out.println(((Admin) user).identity() + "\n");
            } else {
                System.out.println(((SuperAdmin) user).identity() + "\n");
            }
        }
        
        
        // Se busca un libro por ID:
        String bookData = "21dA1SOQDC";
        ArrayList<Book> listB = biblioteca.searchBook(bookData);
        for(Book book : listB) {
            System.out.println("\n" + book.toString());
        }
        
        // NO MODIFICAR (Actualiza la información de los archivos):
        biblioteca.updateInfoInFiles();
        
    }
    
}
