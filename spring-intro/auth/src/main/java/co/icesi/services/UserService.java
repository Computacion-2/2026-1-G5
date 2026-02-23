package co.icesi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.icesi.model.Role;
import co.icesi.model.User;
import co.icesi.repositories.RoleRepository;
import co.icesi.repositories.UserRepository;
import jakarta.annotation.PostConstruct;

@Component
public class UserService {
    
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    @Qualifier("databaseLoader")
    private LoadUserService authService;

    @PostConstruct
    public void init(){
        User u = repository.findByUsername("amunoz");
        Role r = roleRepository.findById(2);

        u.getRoles().add(r);
        r.getUsers().add(u);
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public void setAuthService(LoadUserService authService) {
        this.authService = authService;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addUser(String name, String username, String password, String roles){
        if (name != null && !name.isEmpty() && username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            if(repository.findByUsername(username) != null){
                throw new RuntimeException("Username already exists");
            }
        } else {
            throw new RuntimeException("Name, username and password are required");
            
        }

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);

        String[] rolesArray = roles.split(",");
        for (String roleId : rolesArray) {
            Role role = roleRepository.findById(Long.parseLong(roleId));
            if (role != null) {
                user.getRoles().add(role);
                role.getUsers().add(user);
            }
        }

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
        return authService.isUserValid(username, password);
    }

    public String getUrl() {
        return repository.getUrl();
    }   
}
