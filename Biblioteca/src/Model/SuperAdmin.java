package Model;

import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public final class SuperAdmin extends Admin {
    
    // Constructor:
    public SuperAdmin(String name, String firstLastName, String secondLastName,
                      int userNumber, Date lastLogin) {
        super(name, firstLastName, secondLastName, null, userNumber, null, lastLogin);
        generateID(); // Se genera el ID.
        generatePassword(); // Se genera la constraseña.
    }    
    
    // Genera el ID del superAdmin:
    private void generateID() {
        ID id = new ID();
        char[] idChars = "5uP3R4dm1NrO07".toCharArray();
        id.setID(idChars);
        super.setUserID(id);
    }
    
    
    // Genera la contraseña del SuperAdmin:
    private void generatePassword() {
        Password password = new Password();
        password.createPasswordFromString("xR0074dM1nT0x00");
        super.setPassword(password);
    }
    
    // Añade un usuario a un ArrayList de usuarios.
    // ENTRADA: ArrayList de tipo User y un objeto de tipo User.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean addUserToList(ArrayList<User> usersList, User user){
        
        // Si la lista es nula o ya contiene al usuario:
        if(usersList == null || !(searchUserInList(usersList, String.valueOf(user.getUserID().getCharCode())).isEmpty())) {
            return false;
        }
        
        // Se agrega el usuario a la lista:
        usersList.add(user);
        
        return true;
    }
    
    // Elimina un usuario de un ArrayList de usuarios:
    // ENTRADA: Arraylist de tipo User y un objeto de tipo User.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean deleteUserInList(ArrayList<User> usersList, User user) {
        
        // Si la lista es nula, está vacía, no contiene al usuario o es un superadmin:
        if(usersList == null || usersList.isEmpty() 
           || searchUserInList(usersList, String.valueOf(user.getUserID().getCharCode())).isEmpty() 
           || user instanceof SuperAdmin) {
            return false;
        }
        
        // Se busca el usuario:
        for(int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUserID().equals(user.getUserID().getCharCode()) == true) {
                usersList.remove(i); // Se elimina el usuario si hay una coincidencia.
            }
        }
        
        return true;
    }
    
    // Métodos concretos:
    // Verifica que el password del superAdmin(root) sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if(password == null) {
            return false;
        } else if(password.length == 15) {
            return super.getUserPassword().compare(password);
        }
        return false;
    }

    // Retorna la identifiación del superadmin:
    @Override
    public String identity() {
        StringBuilder str = new StringBuilder();
        
        str.append("ROOT-");
        str.append(String.format("%05d", super.getUserNumber()));
        str.append("\nID: ");
        str.append(String.valueOf(super.getUserID().getCharCode()));
        str.append("\nName: ");
        str.append(super.getName());
        str.append("\nPassword: ");
        str.append(String.valueOf(super.getUserPassword().getPassword()));
        str.append("\nÚltimo acceso: "); 
        str.append(super.getLastLogin().getDateAndTime());
        
        return str.toString();
    }
}
