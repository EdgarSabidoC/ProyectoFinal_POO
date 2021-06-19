package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author edgar
 */
public abstract class User implements Profile, Serializable {
    private String name;
    private String firstLastName;
    private String secondLastName;
    private ID userID;
    private int userNumber;
    private Password password;
    private String lastLogin;
    
    // Constructor:
    public User(String name, String firstLastName, String secondLastName, ID userID, int userNumber, Password password, String lastLogin) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.userID = userID;
        this.userNumber = userNumber;
        this.password = password;
        this.lastLogin = lastLogin;
    }
    
    
    // Getters:
    protected String getName() {
        return name;
    }

    protected String getFirstLastName() {
        return firstLastName;
    }

    protected String getSecondLastName() {
        return secondLastName;
    }

    protected ID getUserID() {
        return userID;
    }
    
    protected Password getUserPassword(){
        return password;
    }

    public String getLastLogin() {
        return lastLogin;
    }
    
    protected int getUserNumber() {
        return userNumber;
    }
    
    // Setters:
    protected void setName(String name) {
        this.name = name;
    }

    protected void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    protected void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    protected void setUserID(ID userID) {
        this.userID = userID;
    }

    protected void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }
    
    protected void setPassword(Password password) {
        this.password = password;
    }
    
    protected void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    // Inicia sesión de usuario:
    public boolean login(ArrayList<User> usersList) {
        
        for(int i = 0; i < usersList.size(); i++) {
            
        }
        
        return true;
    }
    
    // Cambia el nombre completo de un usuario:
    public void rename(String name, String firstLastName, String secondLastName) {
        setName(name);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
    } 
}
