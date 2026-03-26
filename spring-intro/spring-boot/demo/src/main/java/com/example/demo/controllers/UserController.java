package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dtos.user.UserAddDTO;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;




@Controller
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String getUsers(Model model, Authentication auth, HttpServletRequest request) {
        
        model.addAttribute("users", repository.findAll());
        return "users/listUsers";
    }

    @GetMapping("/create-user")
    public String getFormulate() {
        return "users/createUser";
    }

    @PostMapping("/users")
    public String addUsers(@ModelAttribute("user") UserAddDTO dto) {
        System.out.println(dto.getUsername());
        System.out.println(dto.getEmail());
        User user = new User();
        user.setName(dto.getUsername());
        user.setUsername(dto.getEmail());
        user.setPassword("sdas");
        repository.save(user);
        return "redirect:/api/users";
    }


}
