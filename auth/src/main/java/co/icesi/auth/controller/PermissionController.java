package co.icesi.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.icesi.auth.model.Permission;
import co.icesi.auth.service.PermissionService;

@Controller
@RequestMapping("/permissions")
public class PermissionController {
    
    @Autowired
    private PermissionService permissionService;
    
    @GetMapping
    @PreAuthorize("hasAuthority('PERMISSION_LIST')")
    public String listPermissions(Model model) {
        List<Permission> permissions = permissionService.getAllPermissions();
        model.addAttribute("permissions", permissions);
        return "permissions/list";
    }
}
