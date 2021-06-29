package App;

import Model.*;
import Model.Library;
import View.Login;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class PruebaBiblioteca {
    
    public static Library biblioteca;
    public static SuperAdmin root;
    public static User user;

    
    public static void main(String[] args) {
        File adminsDB = new File("Admins.ldb2");
        File membersDB = new File("Members.ldb2");
        File booksDB = new File("Books.ldb2");
        biblioteca = new Library(new ArrayList(), new ArrayList(), new ArrayList<>(), adminsDB, membersDB, booksDB);
        biblioteca.loadInfoFromFiles();
        root = (SuperAdmin) biblioteca.getAdminsList().get(0);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
}
