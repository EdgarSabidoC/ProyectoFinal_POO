package Model;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public interface Profile {
    boolean authenticate(char[] password); // Verifica que el perfil sea el correcto.
    String identity(); // Retorna la identidad del usuario.
}
