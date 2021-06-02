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
        setAdminID(UUID.randomUUID());
    }   
    
    // Getters:
    private UUID getAdminID() {
        return adminID;
    }
    
    // Setters:
    private void setAdminID(UUID adminID) {
        this.adminID = adminID;
    }
    
    
    // Métodos concretos:
    @Override
    public void rename(String name, String firstLastName, String secondLastName) {
        super.setName(name);
        super.setFirstLastName(firstLastName);
        super.setSecondLastName(secondLastName);
    }
    
    // Verifica que el password del Admin sea correcto:
    @Override
    public boolean authenticate(Password password) {
        return adminPassword.compare(password);
    }

    // Retorna el número de indentificación del admin:
    @Override
    public int identity() {
        return getAdminID().hashCode();
    }

    // Método toString:
    @Override
    public String toString() {
        return super.toString() + ", adminID: " + getAdminID();
    }
}
