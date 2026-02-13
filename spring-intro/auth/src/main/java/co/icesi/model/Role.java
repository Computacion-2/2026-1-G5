package co.icesi.model;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private long id;
    private String name;
    private List<User> users;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public List<User> getUsers() {
        if(users == null){
            users = new ArrayList<>();
        }
        return users;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
