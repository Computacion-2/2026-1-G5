package co.icesi.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.icesi.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class UserRepository {

    private List<User> users = new ArrayList<>();

    private long currentId;

    @PostConstruct
    public void init(){
        User user = new User(0, "Name", "username", "password", null);
        save(user);
    }

    public void save(User user){
        currentId++;
        user.setId(currentId);
        users.add(user);    
    }

    public User findById(long id){
        return users.stream().filter(u -> u.getId() == id).findFirst().get();
    }

    public List<User> findAll() {
        return users;
    }
    
}
