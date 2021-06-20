package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author edgar
 */

// MODES: _base38 & _base64, _base64 es el más seguro.
// El tamaño no puede ser menor o igual a 0.
// Si no se le pasa el modo y/o el tamaño, se toma por defecto
// _base64 y un tamaño de 9 chars.
public class ID implements Serializable {
    private String mode;
    private int length;
    private char[] charCode;
    
    public ID(){
        this.mode = "_base64";
        this.length = 9;
    }
    
    public ID(String mode, int length) {
        this.mode = mode;
        this.length = length;
        generateID();
    }
    
    // Getters:
    public char[] getCharCode() {
        return charCode;
    }
    
    public String getMode() {
        return mode;
    }

    public int getLength() {
        return length;
    }
    
    // Setters:
    protected void setMode(String mode) {
        this.mode = mode;
    }

    protected void setLength(int length) {
        this.length = length;
    }

    protected void setID(char[] charCode) {
        this.charCode = charCode;
        setLength(charCode.length);
        setMode("");
    }
   
    // Compara dos charCodes.
    // SALIDA: Retorna true si la operación fue exitosa, false si no.
    public boolean compareID(char[] charCodeID) {
        return Arrays.equals(getCharCode(), charCodeID);
    }

    // Genera un ID aleatorio. En caso de no proporcionar un modo y/o un tamaño,
    // se utiliza por defecto _base64 y un tamaño de 9 chars.
    // SALIDA: Una cadena pseudoaleatoria. 
    // Retorna null si el número de usuario es mayor a 9999
    // o si es menor a 0.
    public char[] generateID() {
        
        // Si no tiene un modo, se usa por defecto: _base64 
        if(getMode() == null || getMode().isEmpty()) {
            setMode("_base64");
        }
        
        // Si no se especificó un tamaño, se usa por defecto: 9
        if(getLength() == 0){
            setLength(9);
        }
        
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
