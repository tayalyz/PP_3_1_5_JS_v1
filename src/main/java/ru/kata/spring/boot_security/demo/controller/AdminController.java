package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.RoleService;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserDetailsService userDetailsService;
    private final RoleService roleService;

    public AdminController(UserDetailsService userDetailsService, RoleService roleService) {
        this.userDetailsService = userDetailsService;
        this.roleService = roleService;
    }
    @GetMapping()
    public String index(Principal principal, Model model) {
        model.addAttribute("myUser", userDetailsService.loadUserByUsername(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/index";
    }
}