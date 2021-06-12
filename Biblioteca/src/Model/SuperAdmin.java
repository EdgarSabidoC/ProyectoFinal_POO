package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author edgar
 */
public final class SuperAdmin extends Admin {
    
    // Constructor:
    public SuperAdmin(String name, String firstLastName, String secondLastName, ID userID, 
                      int userNumber, Password rootPassword, String lastLogin) {
        
        super(name, firstLastName, secondLastName, userID, userNumber, rootPassword, lastLogin);
    }
    
    // Agrega un nuevo admin:
    public boolean addAdmin(File file, Admin admin) {
        try
        { 
            // Se prepara el fichero para escribir al final, append = true:
            BufferedWriter outputFile = new BufferedWriter(new FileWriter(file, true));
 
            outputFile.write(admin.getUserNumber());  // N°. de cliente
            outputFile.write("\\|\\|");                // Separador
            outputFile.write(String.valueOf(admin.getUserID()));  // ID
            outputFile.write("\\|\\|");          
            outputFile.write(admin.getName());    // Nombre
            outputFile.write("\\|\\|");          
            outputFile.write(admin.getFirstLastName());   // Apellido paterno
            outputFile.write("\\|\\|");          
            outputFile.write(admin.getSecondLastName()); // Apellido materno
            outputFile.write("\\|\\|");             
            outputFile.write(String.valueOf(admin.getPassword()));  // Contraseña
            outputFile.write("\\|\\|");          
            outputFile.write(admin.getLastLogin()); // Último acceso
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
    
    // Elimina un admin:
    public boolean deleteAdmin(String admin_ID) {
        return false;
    }
    
    // Métodos concretos:
    // Verifica que el password del superAdmin(root) sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if(password.length == 15) {
            return super.getPassword().compare(password);
        } 
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    // Retorna la identifiación del superadmin:
    @Override
    public String identity() {
        return "ROOT-" + super.getUserID();
    }
}
