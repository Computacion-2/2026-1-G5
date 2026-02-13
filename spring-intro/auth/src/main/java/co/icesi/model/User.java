package co.icesi.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String name;
    private String username;
    private String password;

    private List<Role> roles;

    

    public User(long id, String name, String username, String password, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(){
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        if(roles == null){
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    
    
}
