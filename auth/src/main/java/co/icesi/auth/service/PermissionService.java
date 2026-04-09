package co.icesi.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.auth.model.Permission;
import co.icesi.auth.repository.PermissionRepository;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
    
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }
    
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    
    public Permission updatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
    
    public Permission getPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }
}
