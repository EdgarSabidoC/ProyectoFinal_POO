package Model;

import java.security.SecureRandom;

/**
 *
 * @author edgar
 */
public class Password {
    private int length;
    private char[] password;
    
    public Password(){}
    
    public Password(int length) {
        this.length = length;
    }
    
    // Getters:
    protected int getLength() {
        return length;
    }

    protected char[] getPassword() {
        return password;
    }

    // Setters:    
    public void setLength(int length) {
        this.length = length;
    }
    
    protected void setPassword(char[] password){
        this.password = password;
        setLength(getPassword().length);
    }

    // Genera una contraseña aleatoria:
    private void generatePassword() {
        // String constante con los chars para generar la cadena aleatoria:
        final String chars = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ$%<>¡!¿?#-_{}[]()/\\abcdefghijklmnñopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        
        // Cada iteración del ciclo escoge de manera aleatoria un char del
        // string "chars" y lo anexa al final de la instancia StringBuilder "sb".
        for (int i = 0; i < getLength(); i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        
        // Se asigna el password:
        setPassword(sb.toString().toCharArray()); 
    }
    
    // Modifica el password y su tamaño:
    public boolean createNewPassword() {
        if(length >= 9){
            generatePassword();
            return true;
        }
        return false; // Hubo un error al generar el password.
    }
    
    // Compara dos passwords y retorna true si los dos passwords son iguales.
    // ENTRADA: Arreglo de chars.
    // SALIDA: true si los passwords son iguales, false si no.
    public boolean compare(char[] password) {
        if(getPassword().equals(String.valueOf(password)) == false) {
            System.out.println("ERROR! Contraseña incorrecta.");
        }
        return true;
    }
}
