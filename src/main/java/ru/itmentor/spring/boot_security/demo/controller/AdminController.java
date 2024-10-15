package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.RoleService;
import ru.itmentor.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        addAllRolesToModel(model); // Добавляем роли в модель
        return "admin/addUser"; // Шаблон для добавления пользователя
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.saveUser(user); // Сохраняем нового пользователя
        return "redirect:/admin/users"; // Перенаправление на список пользователей
    }

    private void addAllRolesToModel(Model model) {
        List<Role> roles = roleService.findAll(); // Получаем все роли
        model.addAttribute("roles", roles);
    }

    // Дополнительные методы управления пользователями...
}
