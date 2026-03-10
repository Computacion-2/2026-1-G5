package co.icesi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.icesi.model.Role;
import co.icesi.model.User;
import co.icesi.repositories.RoleRepository;
import co.icesi.repositories.UserRepository;

@Component
public class UserService {
    
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private List<LoadUserService> authService;

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public void setAuthService(List<LoadUserService> authService) {
        this.authService = authService;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addUser(String name, String username, String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        repository.save(user);
    }

    public void addRoleToUser(long idUser, long idRole){

        User user = repository.findById(idUser);
        Role role = roleRepository.findById(idRole);

        user.getRoles().add(role);
        role.getUsers().add(user);

    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public boolean login(String username, String password){
        System.out.println("\n\n\n\n");
        for (LoadUserService loadUserService : authService) {
            System.out.println(loadUserService.getClass().getName());
        }
        return authService.stream().anyMatch(l -> l.isUserValid(username, password));
    }

    public String getUrl() {
        return repository.getUrl();
    }   
}
