/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.security.SecureRandom;

/**
 *
 * @author edgar
 */
public class Password {
    private int length;
    private char[] password;
    
    public Password(int length) {
        setLength(length);
        generatePassword();
    }
    
    // Getter:
    public int getLength() {
        return length;
    }

    public char[] getPassword() {
        return password;
    }

    // Setters:
    // Modifica el password y su tamaño:
    public void setLength(int length) {
        if(length >= 10){
            this.length = length;
            generatePassword();
        }
    }
    
    // Modifica el password:
    private void setPassword(char[] password){
        this.password = password;
    }

    // Genera una contraseña aleatoria:
    private void generatePassword() {
        // String constante con los chars para generar la cadena aleatoria:
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ$%<>¡!¿?#-_{}[]()/\\abcdefghijklmnopqrstuvwxyz0123456789";
 
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
}
