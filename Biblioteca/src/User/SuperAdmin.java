package User;

import java.util.UUID;

/**
 *
 * @author edgar
 */
public final class SuperAdmin extends Admin {
    static private UUID rootID;
    static private Password rootPassword;

    public SuperAdmin(String name, String firstLastName, String secondLastName) {
        super(name, firstLastName, secondLastName);
        generateRootID(); // Se genera el ID del root.
        generateRootPassword(); // Se genera la contraseña de 20 chars.
    }
    
    // Genera el ID del SuperAdmin(root):
    private void generateRootID() {
        SuperAdmin.rootID = UUID.randomUUID();
    }
    
    // Genera el password:
    private void generateRootPassword() {
        SuperAdmin.rootPassword = new Password(20);
    }
    
    // Métodos concretos:
    // Verifica que el password del superAdmin(root) sea correcto:
    @Override
    public boolean authenticate(Password password) {
        if(password.getLength() == 20) {
            return rootPassword.compare(password);
        } 
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    // Retorna el número de indentificación del superAdmin(root):
    @Override
    public String identity() {
        return "ROOT-" + rootID.hashCode();
    }
}
