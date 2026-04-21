package co.icesi.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.icesi.auth.model.User;
import co.icesi.auth.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/api/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.getUserByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "El usuario ya existe");
            return "register";
        }
        if (userService.getUserByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "El email ya está registrado");
            return "register";
        }
        userService.createUser(user);
        return "redirect:/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model, HttpSession session) {
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "dashboard";
    }
}

