package co.icesi.services;

import org.springframework.stereotype.Component;

@Component("myBean")
public class InMemoryUser implements LoadUserService{

    @Override
    public boolean isUserValid(String username, String password) {
        return username.equals("admin") && password.equals("superPassword");
    }
    
}
