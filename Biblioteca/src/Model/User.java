package Model;

import java.io.Serializable;

/**
 *
 * @author Edgar Sabido Cortés, Carlos Antonio Ruíz Domínguez, Luis Alfredo Cota Armenta.
 * 
 */
public abstract class User implements Profile, Serializable {
    private String name;
    private String firstLastName;
    private String secondLastName;
    private ID userID;
    private Password password;
    private Date lastLogin;
    
    // Constructor:
    public User(String name, String firstLastName, String secondLastName, ID userID, Password password, Date lastLogin) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.userID = userID;
        this.password = password;
        this.lastLogin = lastLogin;
    }
    
    
    // Getters:
    public String getName() {
        return name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public ID getUserID() {
        return userID;
    }
    
    protected Password getUserPassword(){
        return password;
    }
    

    public Date getLastLogin() {
        return lastLogin;
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
    
    protected void setPassword(Password password) {
        this.password = password;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    // Cambia el nombre completo de un usuario:
    public void rename(String name, String firstLastName, String secondLastName) {
        setName(name);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
    } 
}
