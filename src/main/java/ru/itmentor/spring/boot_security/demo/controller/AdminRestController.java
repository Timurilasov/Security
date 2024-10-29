package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.RoleServiceImpl;
import ru.itmentor.spring.boot_security.demo.services.UserServicesImpl;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminRestController {
    private final UserServicesImpl userServices;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminRestController(UserServicesImpl userServices, RoleServiceImpl roleService) {
        this.userServices = userServices;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> showUserList() {
        return userServices.getUsersAndRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id) {
        User user = userServices.readUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userServices.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userServices.master(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
