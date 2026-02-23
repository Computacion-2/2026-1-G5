package co.icesi.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.icesi.model.Role;
import jakarta.annotation.PostConstruct;

@Component
public class RoleRepository {

    private List<Role> roles = new ArrayList<>();

    private long currentId;

    @PostConstruct
    public void init(){
        Role r = new Role();
        r.setName("user");

        Role a = new Role();
        a.setName("Admin");

        save(r);
        save(a);
        System.out.println(roles.toString());
    }

    public void save(Role role){
        currentId++;
        role.setId(currentId);
        roles.add(role);    
    }

    public Role findById(long id){
        return roles.stream().filter(u -> u.getId() == id).findFirst().get();
    }

    public List<Role> findAll() {
        return roles;
    }
    
}
