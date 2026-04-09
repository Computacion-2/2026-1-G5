package co.icesi.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.auth.model.Permission;
import co.icesi.auth.model.Role;
import co.icesi.auth.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PermissionService permissionService;
    
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
    
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
    
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
    
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
    
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
    
    public Role addPermissionToRole(Long roleId, Long permissionId) {
        Role role = getRoleById(roleId);
        Permission permission = permissionService.getPermissionById(permissionId);
        if (role != null && permission != null) {
            role.addPermission(permission);
            return roleRepository.save(role);
        }
        return null;
    }
    
    public Role removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = getRoleById(roleId);
        Permission permission = permissionService.getPermissionById(permissionId);
        if (role != null && permission != null) {
            role.removePermission(permission);
            return roleRepository.save(role);
        }
        return null;
    }
}
