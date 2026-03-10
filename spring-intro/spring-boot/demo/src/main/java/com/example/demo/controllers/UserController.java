package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Permission;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;



@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getUsers() {
        return repository.findAll();
    }


    @GetMapping("/users/{userId}")
    public User getUserByUserId(@PathVariable(required = true) Long userId) {

        return repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/users/filter/{username}")
    public Map<String, Object> getUserByUsername(@PathVariable(required = true) String username) {
        User user = repository.findByUsername(username);
        List<Permission> permissions = permissionRepository.getUserPermissions(user.getId());
        return Map.of("user", user, "permissions", permissions);
    }

    @PostMapping("/users")
    public String postMethodName(@RequestBody User entity) {
        repository.save(entity);

        return "success";
    }
    
    @GetMapping("/users/{userId}/{roleId}")
    public String postMethodName(@PathVariable Long userId, @PathVariable Long roleId)throws Exception {
        System.out.println(userId + ":" + roleId);
        service.addRoleToUser(userId, roleId);
        return "success";
    }
    

}
