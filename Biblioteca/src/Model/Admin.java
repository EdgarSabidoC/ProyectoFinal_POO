package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author edgar
 */
public class Admin extends User {

    // Constructor:
    public Admin(String name, String firstLastName, String secondLastName, ID userID,
        int userNumber, Password adminPassword, Date lastLogin) {
        super(name, firstLastName, secondLastName, userID, userNumber, adminPassword, lastLogin);
    }
    
    // Busca un usuario en una lista de usuarios.
    // ENTRADA: ArrayList de tipo User y un arreglo de Strings con los datos del usuario.
    // SALIDA: ArrayList de tipo User.
    public ArrayList<User> searchUserInList(ArrayList<User> usersList, String ... data) {
        
        ArrayList<User> usersFound = new ArrayList<>(); // Lista de coincidencias.
        
        for (int i = 0; i < usersList.size(); i++) {
            for (int j = 0; j < data.length; j++) {
                if (usersList.get(i).getUserID().compareID(data[j].toCharArray())) {
                    // Coincide el ID de usuario:
                    usersFound.add(usersList.get(i)); // Se añadde el usuario a la lista de coincidencias.
                    break;
                } else if (usersList.get(i).getName().equals(data[j])) {
                    if (j + 1 < data.length && usersList.get(i).getFirstLastName().equals(data[j + 1])) {

                        if (j + 2 < data.length && usersList.get(i).getSecondLastName().equals(data[j + 2])) {
                            // Coincide el nombre completo de usuario (nombre y apellidos):
                            usersFound.add(usersList.get(i)); // Se añadde el usuario a la lista de coincidencias.
                            break;
                        }
                    }
                } else if (data[j].matches("-?\\d+") && usersList.get(i).getUserNumber() == Integer.parseInt(data[j])) {
                    // Coincide el número de usuario:
                    usersFound.add(usersList.get(i)); // Se añadde el usuario a la lista de coincidencias.
                    break;
                }
            }
        }

        return usersFound;
    }
    
    // Añade a un miembro a un ArrayList de usuarios.
    // ENTRADA: ArrayList de tipo User y un objeto de tipo Member.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean addMemberToList(ArrayList<User> usersList, Member member) {
        
        // Si la lista es nula o ya contiene al usuario:
        if (usersList == null 
            || !(searchUserInList(usersList, String.valueOf(member.getUserID().getCharCode())).isEmpty())) {
            return false;
        }

        // Se agrega el usuario a la lista:
        usersList.add(member);

        return true;
    }

    // Añade un libro a un ArrayList de libros.
    // ENTRADA: ArrayList de tipo Book, un objeto de tipo Book.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean addBookToList(ArrayList<Book> booksList, Book book) {

        // Si la lista es nula o ya contiene el libro:
        if (booksList == null || booksList.contains(book) == true) {
            return false;
        }

        // Se agrega el libro a la lista:
        booksList.add(book);
        
        // Se ordena la lista de libros por año:
         Collections.sort(booksList, new Comparator<Book>() {
            @Override 
            public int compare(Book book1, Book book2) {
                return book1.getYear()-book2.getYear();
            }
        });
        
        return true;
    }

    // Elimina a un miembro de un ArrayList de usuarios:
    // ENTRADA: Arraylist de tipo User y un objeto de tipo Member.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean deleteMemberInList(ArrayList<User> usersList, Member member) {

        // Si la lista es nula, está vacía o no contiene al usuario:
        if (usersList == null || usersList.isEmpty() 
            || searchUserInList(usersList, String.valueOf(member.getUserID().getCharCode())).isEmpty()) {
            return false;
        }

        // Se busca el usuario:
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUserID().compareID(member.getUserID().getCharCode())) {
                usersList.remove(i); // Se elimina el usuario si hay una coincidencia.
            }
        }

        return true;
    }

    // Elimina un Book de un ArrayList de libros.
    // ENTRADA: ArrayList de tipo Book, un objeto de tipo Book.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean deleteBookInList(ArrayList<Book> booksList, Book book) {

        // Si la lista es nula, está vacía o no contiene el libro:
        if (booksList == null || booksList.isEmpty() || !(booksList.contains(book))) {
            return false;
        }

        // Se busca el libro en la lista:
        for (int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getID().compareID(book.getID().getCharCode())) {
                booksList.remove(i); // Se elimina el libro si hay una coincidencia.
            }
        }

        return true;
    }

    // Métodos concretos:    
    // Verifica que el password del Admin sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if(password == null) {
            return false;
        } else if (password.length == 12) {
            return super.getUserPassword().compare(password);
        }
        return false;
    }

    // Retorna la indentificación del admin:
    @Override
    public String identity() {
        return "ADMN-" + String.format("%05d", super.getUserNumber())
            + "\nID: " + String.valueOf(super.getUserID().getCharCode())
            + "\nNombre: " + super.getName()
            + "\nApellidos: " + super.getFirstLastName() + ' ' + super.getSecondLastName()
            + "\nÚltimo acceso: " + super.getLastLogin().getDateAndTime();
    }
}
