package co.icesi.services;

import org.springframework.stereotype.Component;

@Component
public interface LoadUserService {

    public boolean isUserValid(String username, String password);
    
}
