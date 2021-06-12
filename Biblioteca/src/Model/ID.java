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
    private char[] ID;
    
    public ID(){}
    
    public ID(String mode, int length) {
        this.mode = mode;
        this.length = length;
    }
    
    // Getters:
    public char[] getIDCode() {
        return ID;
    }
    
    protected String getMode() {
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

    public void setID(char[] ID) {
        this.ID = ID;
        setLength(ID.length);
        setMode("");
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
        
        switch(getMode()) {
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
        
        setID(ID);
        
        return ID;
    }
}
