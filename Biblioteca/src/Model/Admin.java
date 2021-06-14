package Model;

import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class Admin extends User {
    
    // Constructor:
    public Admin(String name, String firstLastName, String secondLastName, ID userID,
        int userNumber, Password adminPassword, String lastLogin) {
        super(name, firstLastName, secondLastName, userID, userNumber, adminPassword, lastLogin);
    }
    
    
    // Busca un usuario en una lista de usuarios.
    // ENTRADA: ArrayList de tipo User y un arreglo de Strings con los datos del usuario.
    // SALIDA: ArrayList de tipo User.
    public ArrayList<User> searchUserInList(ArrayList<User> usersList, String[] data) {
        ArrayList<User> usersFound = new ArrayList<>();
        // data[0] = nombre
        // data[1] = apellido1
        // data[2] = apellido2
        // data[3] = ID
        for(int i = 0; i < usersList.size(); i++) {
            
            if(usersList.get(i).getUserID().compareID(data[3].toCharArray())) {
                // Coincide el ID:
                usersFound.add(usersList.get(i)); // Se añadde el usuario a la lista de coincidencias.
            } else if (usersList.get(i).getName().equals(data[0]) &&
                       usersList.get(i).getFirstLastName().equals(data[1]) &&
                       usersList.get(i).getSecondLastName().equals(data[2])) {
                // Coincide el nombre completo:
                usersFound.add(usersList.get(i)); // Se añadde el usuario a la lista de coincidencias.
            }           
        } 
    
        return usersFound;
    }

    
    // Añade un cliente a un ArrayList de usuarios.
    // ENTRADA: ArrayList de tipo User y un objeto de tipo Client.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean addClientToList(ArrayList<User> usersList, Client client){
        
        // Si la lista está vacía o ya contiene al usuario:
        if(usersList.isEmpty() || usersList.contains(client)) {
            return false;
        }
        
        // Se agrega el usuario a la lista:
        usersList.add(client);
        
        return true;
    }
    
    
    // Añade un libro a un ArrayList de libros.
    // ENTRADA: ArrayList de tipo Book, un objeto de tipo Book.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean addBookToList(ArrayList<Book> booksList, Book book) {
        
        // Si la lista está vacía o no contiene el libro:
        if(booksList.contains(book)) {
            return false;
        }
        
        // Se agrega el libro a la lista:
        booksList.add(book);
        
        return true;
    }
    
    
    // Elimina un cliente de un ArrayList de usuarios:
    // ENTRADA: Arraylist de tipo User y un objeto de tipo Client.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean deleteClientInList(ArrayList<User> usersList, Client client) {
        
        // Si la lista está vacía:
        if(usersList.isEmpty() == true) {
            return false;
        }
        
        // Se busca el usuario:
        for(int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUserID().compareID(client.getUserID().getCharCode())) {
                usersList.remove(i); // Se elimina el usuario si hay una coincidencia.
            }
        }
        
        return true;
    }

    
    // Elimina un Book de un ArrayList de libros.
    // ENTRADA: ArrayList de tipo Book, un objeto de tipo Book.
    // SALIDA: Retorna true si se efectuó correctamente la operación y false si no.
    public boolean deleteBookInList(ArrayList<Book> booksList, Book book) {
        
        // Si la lista está vacía o no contiene el libro:
        if(!(booksList.isEmpty()) || !(booksList.contains(book))) {
            return false;
        }
        
        // Se busca el libro en la lista:
        for(int i = 0; i < booksList.size(); i++) {
            if (booksList.get(i).getID().compareID(book.getID().getCharCode())) {
                booksList.remove(i); // Se elimina el libro si hay una coincidencia.
            }
        }
        
        return true;
    }

    
    // Permite regresar un libro:
    public boolean returnABook(ArrayList<Client> listOfClients, Client client, ArrayList<Book> sourceOfBooks, Book book) {
        if (book == null || sourceOfBooks.isEmpty() == true || !(sourceOfBooks.contains(book))) {
            return false;
        }

        int bookIndex = sourceOfBooks.indexOf(book); // Se obtiene el índice del libro.
        sourceOfBooks.get(bookIndex).setBorrowed(false); // Se cambia diponibilidad del libro por no prestado.

        int clientIndex = listOfClients.indexOf(client); // Se obtiene el índice del cliente.

        // Se quita el libro de la cuenta del cliente:
        for (int i = 0; i < client.getBookList().size(); i++) {
            if (client.getBookList().get(i).contains(String.valueOf(book.getID().getCharCode()))) {
                client.getBookList().remove(i);
                break;
            }
        }

        // Se reemplaza el cliente por el actualizado:
        listOfClients.set(clientIndex, client);

        return true;
    }

    
    // Métodos concretos:    
    // Verifica que el password del Admin sea correcto:
    @Override
    public boolean authenticate(char[] password) {
        if (password.length == 12) {
            return super.getPassword().compare(password);
        }
        System.out.println("ERROR! Tamaño de contraseña no válido.");
        return false;
    }

    
    // Retorna la indentificación del admin:
    @Override
    public String identity() {
        return "ADMN-" + getUserID();
    }
}
