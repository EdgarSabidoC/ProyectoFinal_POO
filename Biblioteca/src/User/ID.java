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
    private User user;

    public ID(String mode, int length, User user) {
        this.mode = mode;
        this.length = length;
        this.user = user;
    }
    
    // Genera un ID aleatorio.
    // SALIDA: Una cadena aleatoria. 
    // Retorna null si el número de usuario es mayor a 9999
    // o si es menor a 0.
    public char[] generateID() {
        char[] _base64chars = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz".toCharArray();
        
        if(user.getUserNumber() < 0 || user.getUserNumber() > 9999) {
            System.out.println("Error! Númer de usuario no válido");
            return null;
        }
        
        Random _random = new Random();
        
        char[] nm = {user.getName().charAt(0), user.getFirstLastName().charAt(0), user.getSecondLastName().charAt(0)};
        
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
            ID = (LocalDate.now().getYear()%100+ new String(nm)+String.format("%04d", user.getUserNumber())+sb.toString()).toCharArray();   
        }        
        return ID;
    }
}
