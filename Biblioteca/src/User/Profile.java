package User;

/**
 *
 * @author edgar
 */
public interface Profile {
    boolean authenticate(char[] password); // Verifica que el perfil sea el correcto.
    String identity(); // Retorna la identidad del usuario.
}
