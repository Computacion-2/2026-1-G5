package co.icesi.auth.service;

import co.icesi.auth.model.User;
import co.icesi.auth.model.Role;
import co.icesi.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public User addRoleToUser(Long userId, Long roleId) {
        User user = getUserById(userId);
        Role role = roleService.getRoleById(roleId);
        if (user != null && role != null) {
            user.addRole(role);
            return userRepository.save(user);
        }
        return null;
    }
    
    public User removeRoleFromUser(Long userId, Long roleId) {
        User user = getUserById(userId);
        Role role = roleService.getRoleById(roleId);
        if (user != null && role != null) {
            user.removeRole(role);
            return userRepository.save(user);
        }
        return null;
    }
}
