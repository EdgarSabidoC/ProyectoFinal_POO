package User;

import java.util.UUID;

/**
 *
 * @author edgar
 */
public class Admin extends User {
    private UUID adminID;
    private Password adminPassword;
    
    public Admin(String name, String firstLastName, String secondLastName) {
        super(name, firstLastName, secondLastName);
        generateAdminID(); // Se genera el ID del admin.
        generateAdminPassword(); // Se genera la contraseña de 15 chars.
    }   
    
    // Genera el ID del Admin:
    private void generateAdminID() {
        this.adminID = UUID.randomUUID();
    }
    
    // Genera el password:
    private void generateAdminPassword() {
        this.adminPassword = new Password(15);
    }
    
    // Métodos concretos:    
    // Verifica que el password del Admin sea correcto:
    @Override
    public boolean authenticate(Password password) {
        if(password.getLength() == 15) {
            return this.adminPassword.compare(password);
        } 
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    // Retorna el número de indentificación del admin:
    @Override
    public String identity() {
        return "ADMN-" + this.adminID.hashCode();
    }
}
