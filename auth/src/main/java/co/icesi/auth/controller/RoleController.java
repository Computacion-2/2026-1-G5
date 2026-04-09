package co.icesi.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.icesi.auth.model.Permission;
import co.icesi.auth.model.Role;
import co.icesi.auth.service.PermissionService;
import co.icesi.auth.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PermissionService permissionService;
    
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST')")
    public String listRoles(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        return "roles/list";
    }
    
    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public String newRole(Model model) {
        model.addAttribute("role", new Role());
        return "roles/form";
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public String createRole(@ModelAttribute Role role) {
        roleService.createRole(role);
        return "redirect:/roles";
    }
    
    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    public String editRole(@PathVariable Long id, Model model) {
        Role role = roleService.getRoleById(id);
        List<Permission> permissions = permissionService.getAllPermissions();
        model.addAttribute("role", role);
        model.addAttribute("permissions", permissions);
        return "roles/form";
    }
    
    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    public String updateRole(@PathVariable Long id, @ModelAttribute Role roleDetails) {
        Role role = roleService.getRoleById(id);
        role.setName(roleDetails.getName());
        role.setDescription(roleDetails.getDescription());
        roleService.updateRole(role);
        return "redirect:/roles";
    }
    
    @PostMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return "redirect:/roles";
    }
    
    @PostMapping("/{roleId}/add-permission")
    @PreAuthorize("hasAuthority('ROLE_PERMISSION_ADD')")
    public String addPermissionToRole(@PathVariable Long roleId, @RequestParam Long permissionId) {
        roleService.addPermissionToRole(roleId, permissionId);
        return "redirect:/roles/" + roleId + "/edit";
    }
    
    @PostMapping("/{roleId}/remove-permission")
    @PreAuthorize("hasAuthority('ROLE_PERMISSION_REMOVE')")
    public String removePermissionFromRole(@PathVariable Long roleId, @RequestParam Long permissionId) {
        roleService.removePermissionFromRole(roleId, permissionId);
        return "redirect:/roles/" + roleId + "/edit";
    }
}
