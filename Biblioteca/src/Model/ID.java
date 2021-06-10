package Model;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author edgar
 */
public class ID {
    private String mode;
    private int length;
    
    public ID(String mode, int length) {
        this.mode = mode;
        this.length = length;
    }
    
    // Getters:
    public String getMode() {
        return mode;
    }

    public int getLength() {
        return length;
    }
    
    // Setters:
    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setLength(int length) {
        this.length = length;
    }
    

    // Genera un ID aleatorio.
    // SALIDA: Una cadena aleatoria. 
    // Retorna null si el número de usuario es mayor a 9999
    // o si es menor a 0.
    public char[] generateID(char[] str, int number) {
        char[] _base64chars = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz".toCharArray();
        
        if(number < 0 || number > 9999) {
            System.out.println("Error! Númer de usuario no válido");
            return null;
        }
        
        Random _random = new Random();
        
        StringBuilder sb = null;
        
        switch(mode) {
            case "_base64":
                sb = new StringBuilder(length);
            
                for (int i=0; i<length; i++) { 
                    sb.append(_base64chars[_random.nextInt(64)]);
                }
                break;
            
            case "_base38":
                sb = new StringBuilder(length);
                for (int i=0; i<length; i++) {
                    sb.append(_base64chars[_random.nextInt(38)]);
                }
                break;
        }
        
        char[] ID = null;
        
        if(sb != null) {
            ID = (LocalDate.now().getYear()%100+ new String(str)+String.format("%04d", number)+sb.toString()).toCharArray();   
        }        
        return ID;
    }
}