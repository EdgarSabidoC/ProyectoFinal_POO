package Model;

/**
 *
 * @author edgar
 */
public final class SuperAdmin extends Admin {
    
    // Constructor:
    public SuperAdmin(String name, String firstLastName, String secondLastName, ID user_ID, 
                      int userNumber, Password rootPassword, Date last_login) {
        
        super(name, firstLastName, secondLastName, user_ID, userNumber, rootPassword, last_login);
    }
    
    // Agrega un nuevo admin:
    public boolean addAdmin(Admin admin) {
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
        return "ROOT-" + super.getUser_ID();
    }
}