package co.icesi.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.icesi.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class UserRepository {

    @Value("${data.url}")
    private String url;

    private List<User> users = new ArrayList<>();

    private long currentId;

    @PostConstruct
    public void init(){
        User user = new User(0, "Name", "username", "password", null);
        save(user);
    }

    public String getUrl() {
        return url;
    }

    public void save(User user){
        currentId++;
        user.setId(currentId);
        users.add(user);    
    }

    public User findById(long id){
        return users.stream().filter(u -> u.getId() == id).findFirst().get();
    }

    public User findByUsername(String username){
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().get();
    }
    public List<User> findAll() {
        return users;
    }
    
}
