package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(rollbackOn = Exception.class, value = TxType.REQUIRES_NEW)
    public void addRoleToUser(long userId, long roleId)throws Exception{

        Optional<User> userO = userRepository.findById(userId);
        if(userO.isEmpty()){
            throw new Exception("Not found");
        }

        Role role = roleRepository.findById(roleId).orElse(new Role());

        if(role.getId() == null){
            role.setName("New Role: "+roleId);
            role = roleRepository.save(role);
        }
        try {
            User user = userO.get();
    
            UserRole usRole = new UserRole();
            role = roleRepository.findById(roleId).orElse(new Role());
            usRole.setRole(role);
            usRole.setUser(user);
    
            user.getRoles().add(usRole);
    
            userRepository.save(user);
            
        } catch (RuntimeException e) {
            throw new Exception("Error");
        }

    }

    public User createUser(String name, String username, String password){

        if(name == null ||username == null || password == null){
            throw new RuntimeException("Validations fail");
        }
        User u = new User(null, name, username, password, null);

        return userRepository.save(u);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User with username doesnt exists");
        }

        UserDetails details= new UserDetailCustome();

       return details;
    }
}
