package View;

import Model.User;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public class Validate {
    // Valida si existe el ID de un usuario:
    public boolean isValidID(ArrayList<User> usersList, char[] ID){
        for(int i = 0; i < usersList.size(); i++) {
           if(usersList.get(i).getUserID().compareID(ID)) {
               return true;
           }
        }
        
        return false;
    }
    
    // Valida que se ingrese un número entero positivo (que la cadena sea no vacía y sólo números).
    // Para número de páginas y año.
    public boolean isValidInteger(JTextField textField, JLabel label) {
        boolean isValid = true;
        
        try {
            if(textField.getText() == null) {
                // Si es null:
                throw new IntervalException("NULL");
            }
            
            if(textField.getText().isEmpty()) {
                // Si el campo está vacío:
                throw new IntervalException("El campo está vacío.");
            }
            
            if(!(textField.getText().matches(".*\\d+.*"))) {
                // Si no son números:
                throw new IntervalException("Ingresar sólo números.");
            }
            
            if(Integer.parseInt(textField.getText()) < 0) {
                // Si es un número negativo:
                throw new IntervalException("No se aceptan números menores a 0.");
            }
            
        } catch (ArithmeticException error1) {
            label.setText(error1.getMessage());
            textField.setBackground(Color.PINK);
            label.setVisible(true);
            isValid = false;
        } catch (IntervalException error2) {
            label.setText(error2.getMessage());
            textField.setBackground(Color.PINK);
            label.setVisible(true);
            isValid = false;
        } catch (NumberFormatException error3) {
            label.setText("Ingresar sólo números.");
            textField.setBackground(Color.PINK);
            label.setVisible(true);
            isValid = false;
        }
        
        return isValid;
    }
    
    // Valida que se ingrese una cadena no vacía (sin números).
    // Sólo espacios y letras.
    // Para nombres
    public boolean isValidStringWithoutDigits(JTextField textField, JLabel label) {
        boolean isValid = true;

        try {
            if(textField.getText() == null) {
                // Si es null:
                throw new IntervalException("NULL");
            }
            
            if(textField.getText().isEmpty()) {
                // Si el campo está vacío:
                throw new IntervalException("El campo está vacío.");
            }
            
            if(textField.getText().matches(".*\\d+.*")) {
                // Si son números:
                throw new IntervalException("Sólo se pueden usar letras.");
            }
            
        } catch (IntervalException error) {
            textField.setBackground(Color.PINK);
            label.setText(error.getMessage());
            label.setVisible(true);
            isValid = false;
        }
        
        return isValid;
    }
    
    // Valida que se ingrese una cadena no vacía (acepta cualquier cosa).
    // Para títulos, editoriales, etc.
    public boolean isValidString(JTextField textField, JLabel label) {
        boolean isValid = true;

        try {
            if(textField.getText() == null) {
                // Si es null:
                throw new IntervalException("NULL");
            }
            
            if(textField.getText().isEmpty()) {
                // Si el campo está vacío:
                throw new IntervalException("El campo está vacío.");
            }
        } catch (IntervalException error) {
            textField.setBackground(Color.PINK);
            label.setText(error.getMessage());
            label.setVisible(true);
            isValid = false;
        }
        
        return isValid;
    }
    
    // Valida que se ingrese una cadena no vacía que acepta cualquier char (incluidos números).
    // Para passwords.
    public boolean isValidStringWithDigits(JTextField textField, JLabel label) {
        boolean isValid = true;

        try {
            if(textField.getText() == null) {
                // Si es null:
                throw new IntervalException("NULL");
            }
            
            if(textField.getText().isEmpty()) {
                // Si el campo está vacío:
                throw new IntervalException("Campo vacío.");
            }
            
            if(textField.getText().contains(" ")) {
                // Si contiene espacios:
                throw new IntervalException("No se permiten espacios.");
            }
        } catch (IntervalException error) {
            textField.setBackground(Color.PINK);
            label.setText(error.getMessage());
            label.setVisible(true);
            isValid = false;
        }
        
        return isValid;
    }
    
    // Valida que se ingrese una cadena no vacía de sólo números; sin espacios, letras ni caracteres especiales.
    // Para ISBN.
    public boolean isValidStringOnlyDigits(JTextField textField, JLabel label) {
        boolean isValid = true;

        try {
            if(textField.getText() == null) {
                // Si es null:
                throw new IntervalException("NULL");
            }
            
            if(textField.getText().isEmpty()) {
                // Si el campo está vacío:
                throw new IntervalException("Campo vacío.");
            }
            
            if(!(textField.getText().matches(".*\\d+.*"))) {
                // Si no son números:
                throw new IntervalException("Ingresar sólo números.");
            }
            
            if(textField.getText().contains(" ")) {
                // Si contiene espacios:
                throw new IntervalException("No se permiten espacios.");
            }
        } catch (IntervalException error) {
            textField.setBackground(Color.PINK);
            label.setText(error.getMessage());
            label.setVisible(true);
            isValid = false;
        }
        
        return isValid;
    }
}
