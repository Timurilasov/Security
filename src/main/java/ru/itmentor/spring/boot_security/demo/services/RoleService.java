package ru.itmentor.spring.boot_security.demo.services;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();

    Role findByName(String roleAdmin);

    List<Role> findAll();
}
