/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.security.SecureRandom;
import java.util.UUID;

/**
 *
 * @author edgar
 */
public abstract class User implements Profile {
    private String name;
    private String firstLastName;
    private String secondLastName;
    
    // Constructor:
    public User(String name, String firstLastName, String secondLastName) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
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
    
    // Setters:
    public void setName(String name) {
        this.name = name;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    @Override
    public String toString() {
        return "name: " + getName() + ", firstLastName: " + getFirstLastName() + ", secondLastName: " + getSecondLastName();
    }
    
    
}
