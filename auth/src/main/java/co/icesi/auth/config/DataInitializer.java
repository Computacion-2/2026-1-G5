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

import java.util.Arrays;
import java.util.List;

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
        if (permissionRepository.count() == 0) {
            createPermissions();
        }
        
        if (roleRepository.findByName("ADMIN") == null) {
            createRoles();
        }
        
        if (userRepository.count() <= 2) {
            createUsers();
        }
    }
    
    private void createPermissions() {
        // User Permissions
        savePermission("USER_LIST", "Listar usuarios");
        savePermission("USER_CREATE", "Crear usuarios");
        savePermission("USER_EDIT", "Editar usuarios");
        savePermission("USER_DELETE", "Eliminar usuarios");
        
        // Role Permissions
        savePermission("ROLE_LIST", "Listar roles");
        savePermission("ROLE_CREATE", "Crear roles");
        savePermission("ROLE_EDIT", "Editar roles");
        savePermission("ROLE_DELETE", "Eliminar roles");
        
        // Permission Permissions
        savePermission("PERMISSION_LIST", "Listar permisos");

        // Course Permissions
        savePermission("COURSE_READ", "Leer cursos");
        savePermission("COURSE_CREATE", "Crear cursos");
        savePermission("COURSE_UPDATE", "Actualizar cursos");
        savePermission("COURSE_DELETE", "Eliminar cursos");
        savePermission("COURSE_ENROLL", "Matricular estudiantes");

        // Activity Permissions
        savePermission("ACTIVITY_READ", "Leer actividades");
        savePermission("ACTIVITY_CREATE", "Crear actividades");
        savePermission("ACTIVITY_UPDATE", "Actualizar actividades");
        savePermission("ACTIVITY_DELETE", "Eliminar actividades");

        // Submission Permissions
        savePermission("SUBMISSION_READ", "Leer entregas");
        savePermission("SUBMISSION_CREATE", "Crear entregas");
        savePermission("SUBMISSION_UPDATE", "Actualizar entregas");
        savePermission("SUBMISSION_DELETE", "Eliminar entregas");
        savePermission("SUBMISSION_GRADE", "Calificar entregas");
    }

    private void savePermission(String name, String description) {
        permissionRepository.save(new Permission(null, name, description));
    }
    
    private void createRoles() {
        // ADMIN Role
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        adminRole.setDescription("Rol de administrador con todos los permisos");
        permissionRepository.findAll().forEach(adminRole::addPermission);
        roleRepository.save(adminRole);

        // PROFESSOR Role
        Role professorRole = new Role();
        professorRole.setName("PROFESSOR");
        professorRole.setDescription("Rol de profesor");
        List<String> profPerms = Arrays.asList(
            "COURSE_READ", "COURSE_UPDATE", "COURSE_ENROLL",
            "ACTIVITY_READ", "ACTIVITY_CREATE", "ACTIVITY_UPDATE", "ACTIVITY_DELETE",
            "SUBMISSION_READ", "SUBMISSION_GRADE"
        );
        permissionRepository.findAll().stream()
            .filter(p -> profPerms.contains(p.getName()))
            .forEach(professorRole::addPermission);
        roleRepository.save(professorRole);

        // STUDENT Role
        Role studentRole = new Role();
        studentRole.setName("STUDENT");
        studentRole.setDescription("Rol de estudiante");
        List<String> studentPerms = Arrays.asList(
            "COURSE_READ", "ACTIVITY_READ",
            "SUBMISSION_READ", "SUBMISSION_CREATE", "SUBMISSION_UPDATE"
        );
        permissionRepository.findAll().stream()
            .filter(p -> studentPerms.contains(p.getName()))
            .forEach(studentRole::addPermission);
        roleRepository.save(studentRole);
    }
    
    private void createUsers() {
        createUser("admin", "admin@icesi.edu.co", "admin123", "Admin", "Sistema", "ADMIN");
        createUser("profesor", "profesor@icesi.edu.co", "prof123", "Profesor", "Icesi", "PROFESSOR");
        createUser("estudiante", "estudiante@icesi.edu.co", "est123", "Estudiante", "Icesi", "STUDENT");
    }

    private void createUser(String username, String email, String password, String firstName, String lastName, String roleName) {
        if (userRepository.findByUsername(username).isPresent()) return;
        
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);
        
        Role role = roleRepository.findByName(roleName);
        if (role != null) {
            user.addRole(role);
        }
        userRepository.save(user);
    }
}
