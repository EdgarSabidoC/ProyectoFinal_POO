package User;

/**
 *
 * @author edgar
 */
public final class SuperAdmin extends Admin implements AdminManagement {
    
    // Constructor:
    public SuperAdmin(String name, String firstLastName, String secondLastName, ID user_ID, Password rootPassword, Date last_login) {
        super(name, firstLastName, secondLastName, user_ID, rootPassword, last_login);
    }
    
    // Agrega un nuevo admin:
    @Override
    public boolean addAdmin(Admin admin) {
        return false;
    }
    
    // Elimina un admin:
    @Override
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
