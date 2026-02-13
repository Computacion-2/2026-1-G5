package co.icesi.services;

import java.util.List;

import co.icesi.model.Role;
import co.icesi.model.User;
import co.icesi.repositories.RoleRepository;
import co.icesi.repositories.UserRepository;

public class UserService {
    
    private UserRepository repository;
    private RoleRepository roleRepository;

    public void setRepository(UserRepository repository) {
        this.repository = repository;
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
}
