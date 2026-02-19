package co.icesi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.icesi.model.Role;
import co.icesi.repositories.RoleRepository;

public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
