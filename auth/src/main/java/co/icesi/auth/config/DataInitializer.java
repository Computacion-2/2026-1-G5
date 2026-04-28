package co.icesi.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.icesi.auth.model.Permission;
import co.icesi.auth.model.Role;
import co.icesi.auth.model.User;
import co.icesi.auth.repository.PermissionRepository;
import co.icesi.auth.repository.RoleRepository;
import co.icesi.auth.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Crear permisos si no existen
        if (permissionRepository.count() == 0) {
            createPermissions();
        }
        
        // Crear rol de administrador si no existe
        if (roleRepository.findByName("ADMIN") == null) {
            createAdminRole();
        }
        
        // Crear usuario administrador si no existe
        if (userRepository.findByUsername("admin").isEmpty()) {
            createAdminUser();
        }
    }
    
    private void createPermissions() {
        // Permisos de Usuario
        permissionRepository.save(new Permission(null, "USER_LIST", "Listar usuarios"));
        permissionRepository.save(new Permission(null, "USER_CREATE", "Crear usuarios"));
        permissionRepository.save(new Permission(null, "USER_EDIT", "Editar usuarios"));
        permissionRepository.save(new Permission(null, "USER_DELETE", "Eliminar usuarios"));
        permissionRepository.save(new Permission(null, "USER_ROLE_ASSIGN", "Asignar roles a usuarios"));
        permissionRepository.save(new Permission(null, "USER_ROLE_REMOVE", "Remover roles de usuarios"));
        
        // Permisos de Roles
        permissionRepository.save(new Permission(null, "ROLE_LIST", "Listar roles"));
        permissionRepository.save(new Permission(null, "ROLE_CREATE", "Crear roles"));
        permissionRepository.save(new Permission(null, "ROLE_EDIT", "Editar roles"));
        permissionRepository.save(new Permission(null, "ROLE_DELETE", "Eliminar roles"));
        permissionRepository.save(new Permission(null, "ROLE_PERMISSION_ADD", "Agregar permisos a roles"));
        permissionRepository.save(new Permission(null, "ROLE_PERMISSION_REMOVE", "Remover permisos de roles"));
        
        // Permisos de Permisos
        permissionRepository.save(new Permission(null, "PERMISSION_LIST", "Listar permisos"));
    }
    
    private void createAdminRole() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        adminRole.setDescription("Rol de administrador con todos los permisos");
        
        // Agregar todos los permisos al rol ADMIN
        permissionRepository.findAll().forEach(adminRole::addPermission);

        Role userRole = new Role();
        userRole.setName("USER");
        userRole.setDescription("Rol de usuario con permisos limitados");
        permissionRepository.findAll().stream().filter(p -> p.getName().contains("LIST")).forEach(userRole::addPermission);

        roleRepository.save(userRole);
        roleRepository.save(adminRole);
    }
    
    private void createAdminUser() {
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setEmail("admin@icesi.edu.co");
        adminUser.setPassword(passwordEncoder.encode("admin123"));
        adminUser.setFirstName("Administrador");
        adminUser.setLastName("Sistema");
        adminUser.setEnabled(true);
        
        // Agregar rol ADMIN
        Role adminRole = roleRepository.findByName("ADMIN");
        if (adminRole != null) {
            adminUser.addRole(adminRole);
        }
        
        userRepository.save(adminUser);
        
        System.out.println("========================================");
        System.out.println("USUARIO ADMINISTRADOR CREADO");
        System.out.println("Usuario: admin");
        System.out.println("Contraseña: admin123");
        System.out.println("========================================");
    }
}
