package Model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author edgar
 */
public class ID {
    private String mode;
    private int length;
    private char[] charCode;
    
    public ID(){}
    
    public ID(String mode, int length) {
        this.mode = mode;
        this.length = length;
        generateID();
    }
    
    // Getters:
    public char[] getCharCode() {
        return charCode;
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

    public void setID(char[] charCode) {
        this.charCode = charCode;
        setLength(charCode.length);
        setMode("");
    }
   
    // Compara dos charCodes:
    public boolean compareID(char[] charCodeID) {
        return Arrays.equals(getCharCode(), charCodeID);
    }

    // Genera un ID aleatorio.
    // SALIDA: Una cadena aleatoria. 
    // Retorna null si el número de usuario es mayor a 9999
    // o si es menor a 0.
    public char[] generateID() {
        char[] _base64chars = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz".toCharArray();
        
        Random _random = new Random();
        
        StringBuilder sb = null;
        
        switch(getMode()) {
            case "_base64":
                sb = new StringBuilder(length-2);
            
                for (int i=0; i<length-2; i++) { 
                    sb.append(_base64chars[_random.nextInt(64)]);
                }
                break;
            
            case "_base38":
                sb = new StringBuilder(length-2);
                for (int i=0; i<length-2; i++) {
                    sb.append(_base64chars[_random.nextInt(38)]);
                }
                break;
        }
        
        char[] ID = null;
        
        if(sb != null) {
            ID = (LocalDate.now().getYear()%100 + sb.toString()).toCharArray();   
        }       
        
        setID(ID);
        
        return ID;
    }
}
