package Model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */

// Si no se le proporciona un tamaño al password al ser instanciado
// se toma por defecto un tamaño de 10 chars.
public class Password implements Serializable{
    private int length;
    private char[] password;
    
    public Password(){
        this.length = 10;
    }
    
    public Password(int length) {
        this.length = length;
        generatePassword();
    }
    
    // Getters:
    protected int getLength() {
        return length;
    }

    public char[] getPasswordCode() {
        return password;
    }

    // Setters:    
    protected void setLength(int length) {
        this.length = length;
    }
    
    protected void setPassword(char[] password){
        this.password = password;
        setLength(getPasswordCode().length);
    }

    // Crea un password a partir de un String:
    protected void createPasswordFromString(String str) {
        setPassword(str.toCharArray());
    }
    
    // Genera una contraseña aleatoria:
    private boolean generatePassword() {
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
        
        // La nueva contraseña se asigna si la cadena no es vacía:
        if(!(sb.isEmpty())) {
            // Se asigna el password:
            setPassword(sb.toString().toCharArray()); 
            return true;
        } else {
            return false;
        }
    }
    
    // Crea un nuevo password (si no se especifica tamaño toma 10 por defecto):
    public boolean createNewPassword() {
        if(getLength() >= 9){
            return generatePassword();
        }
        return false; // Hubo un error al generar el password.
    }
    
    // Retorna el hash del password:
    @Override
    public int hashCode() {
        return Arrays.hashCode(getPasswordCode());
    }

    // Verifica que todos los atributos de ambos objetos sean iguales:
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Password other = (Password) obj;
        if (this.length != other.length) {
            return false;
        }
        if (!Arrays.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    // Compara dos passwords y retorna true si los dos passwords son iguales.
    // ENTRADA: Arreglo de chars.
    // SALIDA: true si los passwords son iguales, false si no.
    public boolean compare(char[] password) {
        return Arrays.equals(getPasswordCode(), password);
    }
}
