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
public abstract class Library {
    private String fileName;
    private String fileExtension;
    private File file;
    
    // Constructor:
    public Library(String fileName, String fileExtension) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        generateFile();
    }
    
    // Getters:
    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public File getFile() {
        return file;
    }
    
    // Setters:
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    
    // Genera un fichero con el nombre y la extensión designada:
    private void generateFile() {
        
        if(!getFileName().isEmpty() && !getFileExtension().isEmpty()) {
            
            // Se crea el objeto File con el nombre y la extensión:
            File newFile = new File(getFileName() + getFileExtension());
            
            try{
                // Se verifica si no existe el archivo:
                if(!(newFile.exists())){
                    
                    // Se crea el archivo en caso de no existir:
                    newFile.createNewFile();
                }
                
                // Se asigna el archivo al atributo:
                setFile(newFile);
                
            } catch(IOException fileError) {
                
                // Si hubo un error al generar el archivo:
                System.out.println("Error: " + fileError.getMessage());
            }
        }
        // Si hubo un error en la asignación de nombre o extensión:
        System.out.println("Error al generar el archivo de la base de datos.\n");
    }
    
    // Busca una cadena dentro de un archivo y retorna una lista con los libros que coincidan
    // en alguno de sus atributos con la cadena, si no hay ninguno, retorna una lista vacía:
    public ArrayList<Book> searchBook(String word) {
        
        // Se crea la lista de libros vacía:
        ArrayList<Book> listOfBooks = new ArrayList<>();
        
        // Se verifica si existe el archivo:
        if(!(getFile().exists())) {
            // Si no existe el archivo:
            System.out.println("Error, no se encontró el archivo " + getFileName() + getFileExtension() + "\n");
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
                        String bookAuthor = split[0].trim();
                        int bookYear = Integer.parseInt(split[1].trim());
                        String bookTitle = split[2].trim();
                        String bookEdition = split[3].trim();
                        String bookEditorial = split[4].trim();
                        int bookPages = Integer.parseInt(split[5].trim());
                        int bookISBN = Integer.parseInt(split[6].trim());
                        String available = split[7].trim();
                        
                        boolean bookBorrowed = true;
                        
                        // Si el libro está disponible:
                        if(available.equals("Disponible")) {
                            bookBorrowed = false;
                        }
                        
                        // Se ingresa el libro a la lista de libros:
                        listOfBooks.add(new Book(bookAuthor, bookYear, bookTitle, bookEdition, 
                                        bookEditorial, bookPages, bookISBN, bookBorrowed));
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

