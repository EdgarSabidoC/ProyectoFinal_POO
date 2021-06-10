package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public class Library {
    private String fileName;
    private File file;
    
    // Constructor:
    public Library(String fileName, File file) {
        this.fileName = fileName;
        this.file = file;
    }
    
    // Getters:
    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }
    
    // Setters:
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    
    // Busca una cadena dentro de un archivo y retorna una lista con los libros que coincidan
    // en alguno de sus atributos con la cadena, si no hay ninguno, retorna una lista vacía:
    public ArrayList<Book> searchBook(String word) {
        
        // Se crea la lista de libros vacía:
        ArrayList<Book> listOfBooks = new ArrayList<>();
        
        // Se verifica si existe el archivo:
        if(!(getFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getFileName() + "\n");
        } else {  
            // Si el archivo existe:
            try{
                // Se lee el archivo:
                BufferedReader inputFile = new BufferedReader(new FileReader(getFile()));
                
                String line; // Variable temporal para almacenar la línea del archivo.
                
                // Se recorre todo el archivo línea por línea:
                while((line = inputFile.readLine()) != null) {
                    
                    // Se verifica si la línea contiene la palabra que se busca:
                    if(line.contains(word) == true) {
                       
                        // Se generan los tokens de los atributos del libro:
                        String[] split = line.split("\\|\\|"); // Utiliza || como separador.                        
                        
                        // Se quitan los espacios al inicio y al final de los tokens (por si hay) con String.trim()
                        // También se hace parsing para cambiar el tipo de dato al del atributo correspondiente del libro.
                        char[] bookID = (split[0].trim()).toCharArray();
                        String bookAuthor = split[1].trim();
                        int bookYear = Integer.parseInt(split[2].trim());
                        String bookTitle = split[3].trim();
                        String bookEdition = split[4].trim();
                        String bookEditorial = split[5].trim();
                        int bookPages = Integer.parseInt(split[6].trim());
                        int bookISBN = Integer.parseInt(split[7].trim());
                        String available = split[8].trim();
                        
                        boolean bookBorrowed = true;
                        
                        // Si el libro está disponible:
                        if(available.equals("Disponible")) {
                            bookBorrowed = false;
                        }
                        
                        // Se ingresa el libro a la lista de libros:
                        listOfBooks.add(new Book(bookAuthor, bookYear, bookTitle, bookEdition, 
                                        bookEditorial, bookPages, bookISBN, bookID, bookBorrowed));
                    }
                }   
            } catch(IOException fileError) {
                // Si hubo un error al leer el archivo:
                System.out.println("Error: " + fileError.getMessage());
            }
        }
        // Se retorna la lista de libros (vacía si no contiene ningún libro):
        return listOfBooks;
    }
    
}

