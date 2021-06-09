package User;

import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author edgar
 */
public class ID {
    private String mode;
    private int length;
    private String user_name;
    private String user_lastname1;
    private String user_lastname2;
    private int user_number;

    public ID(String mode, int length, String user_name, String user_lastname1, String user_lastname2, int user_number) {
        this.mode = mode;
        this.length = length;
        this.user_name = user_name;
        this.user_lastname1 = user_lastname1;
        this.user_lastname2 = user_lastname2;
        this.user_number = user_number;
    }
    
    // Genera un ID aleatorio.
    // SALIDA: Una cadena aleatoria. 
    // Retorna null si el número de usuario es mayor a 9999
    // o si es menor a 0.
    public char[] generateID() {
        char[] _base64chars = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz".toCharArray();
        
        if(user_number < 0 || user_number > 9999) {
            System.out.println("Error! Númer de usuario no válido");
            return null;
        }
        
        Random _random = new Random();
        
        char[] nm = {user_name.charAt(0), user_lastname1.charAt(0), user_lastname2.charAt(0)};
        
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
            ID = (LocalDate.now().getYear()%100+ new String(nm)+String.format("%04d", user_number)+sb.toString()).toCharArray();   
        }        
        return ID;
    }
}
