package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class Admin extends User {

    // Constructor:
    public Admin(String name, String firstLastName, String secondLastName, ID userID, 
                 Password adminPassword, Date lastLogin) {
        super(name, firstLastName, secondLastName, userID, adminPassword, lastLogin);
    }
    
    // Devuelve un libro:
    // ENTRADA: Lista de miembros, lista de libros, miembro y libro.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean returnABook(ArrayList<User> membersList, ArrayList<Book> booksList, Member member, Book book) {

        if (!(booksList.contains(book)) || !(membersList.contains(member))) {
            // Si en las listas no se encuentra el libro o al miembro:
            return false;
        }
        
        // Se elimina el libro de la colección del miembro:
        int memberIndex = membersList.indexOf(member);
        int bookIndex = ((Member) membersList.get(memberIndex)).getBookList().indexOf(book);
        ((Member) membersList.get(memberIndex)).removeBook(book);
        
        // Se cambia la fecha de devolución a N/A:
        booksList.get(booksList.indexOf(book)).setReturnDate(null);
        
        
        // Se cambia el estado de prestado del libro:
        booksList.get(booksList.indexOf(book)).setBorrowed(false);
        
        
        return true;
    }
    
    // Busca un usuario en una lista de usuarios.
    // ENTRADA: ArrayList de tipo User y un arreglo de Strings con los datos del usuario.
    // SALIDA: ArrayList de tipo User.
    public ArrayList<User> searchUserInList(ArrayList<User> usersList, String ... data) {
        
        ArrayList<User> usersFound = new ArrayList<>(); // Lista de coincidencias.
        
        // Se recorre la lista de usuarios ingresado (Admins o Members):
        for (int i = 0; i < usersList.size(); i++) {
            // Se recorren los Strings ingresados:
            for (int j = 0; j < data.length; j++) {
                if (usersList.get(i).getUserID().compareID(data[j].toCharArray())) {
                    // Coincide el ID de usuario:
                    usersFound.add(usersList.get(i)); // Se añadde el usuario a la lista de coincidencias.
                    break;
                } else if (usersList.get(i).getName().contains(data[j])) {
                    if (usersList.get(i).getFirstLastName().contains(data[j])) {
                        if (usersList.get(i).getSecondLastName().contains(data[j])) {
                            // Coincide el nombre completo de usuario (nombre y apellidos):
                            usersFound.add(usersList.get(i)); // Se añadde el usuario a la lista de coincidencias.
                            break;
                        }
                        // Coincide el nombre y primer apellido del usuario:
                        usersFound.add(usersList.get(i)); // Se añadde el usuario a la lista de coincidencias.
                        break;
                    }
                    // Coincide el nombre del usuario:
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
        
        // Si la lista es nula o ya contiene al miembro:
        if (usersList == null 
            || !(searchUserInList(usersList, String.valueOf(member.getUserID().getCharCode())).isEmpty())) {
            return false;
        }

        // Se agrega al miembro a la lista:
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

        // Si la lista es nula, está vacía o no contiene al miembro:
        if (usersList == null || usersList.isEmpty() 
            || searchUserInList(usersList, String.valueOf(member.getUserID().getCharCode())).isEmpty()) {
            return false;
        }

        // Se busca al miembro:
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUserID().compareID(member.getUserID().getCharCode())) {
                usersList.remove(i); // Se elimina al miembro si hay una coincidencia.
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
        StringBuilder str = new StringBuilder();
        
        str.append("ADMN"); 
        str.append("\nID: "); 
        str.append(String.valueOf(super.getUserID().getCharCode()));
        str.append("\nNombre: ");
        str.append(super.getName());
        str.append("\nApellidos: ");
        str.append(super.getFirstLastName());
        str.append(' '); 
        str.append(super.getSecondLastName());
        str.append("\nÚltimo acceso: ");
        str.append(super.getLastLogin().getDateAndTime());
        
        return str.toString();
    }
}
