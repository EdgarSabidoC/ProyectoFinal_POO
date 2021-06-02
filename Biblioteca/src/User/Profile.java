package User;

import java.util.UUID;

/**
 *
 * @author edgar
 */
public interface Profile {
    void rename(String name, String firstLastName, String secondLastName); // Cambia el nombre y apellidos.
    boolean authenticate(Password password); // Verifica que el perfil sea el correcto.
    int identity(); // Retorna la identidad del usuario.
}
