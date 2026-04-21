package co.icesi.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.icesi.auth.model.User;
import co.icesi.auth.service.RoleService;
import co.icesi.auth.service.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @GetMapping
    @PreAuthorize("hasAuthority('USER_LIST')")
    public String listUsers(Model model, Authentication authentication) {
        List<User> users = userService.getAllUsers();
        User currentUser = userService.getUserByUsername(authentication.getName());
        model.addAttribute("users", users);
        model.addAttribute("currentUser", currentUser);
        return "users/list";
    }
    
    @GetMapping("/new")
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/form";
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public String createUser(@Valid @ModelAttribute User user, BindingResult result, @RequestParam(required = false) Long roleId) {
        if (result.hasErrors()) {
            return "users/form";
        }
        
        userService.createUser(user);
        if (roleId != null) {
            userService.addRoleToUser(user.getId(), roleId);
        }
        return "redirect:/users";
    }
    
    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('USER_EDIT')")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/form";
    }
    
    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('USER_EDIT')")
    public String updateUser(@PathVariable Long id, @ModelAttribute User userDetails) {
        User user = userService.getUserById(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        userService.updateUser(user);
        return "redirect:/users";
    }
    
    @PostMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    
    @PostMapping("/{userId}/add-role")
    @PreAuthorize("hasPermission(USER_ROLE_ASSIGN')")
    public String addRoleToUser(@PathVariable Long userId, @RequestParam Long roleId) {
        userService.addRoleToUser(userId, roleId);
        return "redirect:/users";
    }
    
    @PostMapping("/{userId}/remove-role")
    @PreAuthorize("hasPermission(USER_ROLE_REMOVE')")
    public String removeRoleFromUser(@PathVariable Long userId, @RequestParam Long roleId) {
        userService.removeRoleFromUser(userId, roleId);
        return "redirect:/users";
    }
}
