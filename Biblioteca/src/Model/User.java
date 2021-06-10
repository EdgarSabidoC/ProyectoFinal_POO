package Model;

/**
 *
 * @author edgar
 */
public abstract class User implements Profile {
    private String name;
    private String firstLastName;
    private String secondLastName;
    private ID user_ID;
    private int userNumber;
    private Password password;
    private Date last_login;
    
    // Constructor:
    public User(String name, String firstLastName, String secondLastName, ID user_ID, int userNumber, Password password, Date last_login) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.user_ID = user_ID;
        this.userNumber = userNumber;
        this.password = password;
        this.last_login = last_login;
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

    protected ID getUser_ID() {
        return user_ID;
    }
    
    protected Password getPassword(){
        return password;
    }

    public Date getLast_login() {
        return last_login;
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

    protected void setUser_ID(ID user_ID) {
        this.user_ID = user_ID;
    }

    protected void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }
    
    protected void setPassword(Password password) {
        this.password = password;
    }
    
    protected void setLast_login(Date last_login) {
        this.last_login = last_login;
    }
    
    
    // Cambia el nombre completo de un usuario:
    public void rename(String name, String firstLastName, String secondLastName) {
        setName(name);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
    } 
}
