package co.icesi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.icesi.model.User;
import co.icesi.repositories.UserRepository;

@Component
public class DatabaseLoader implements LoadUserService{

    @Autowired
    private UserRepository repository;

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isUserValid(String username, String password) {
        User u = repository.findByUsername(username);
        if (u != null) {
            return u.getPassword().equals(password);
        }

        return false;
    }
    
}
