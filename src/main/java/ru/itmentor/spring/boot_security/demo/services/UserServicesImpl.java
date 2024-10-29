package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repositories.RoleRepository; // Добавьте необходимый импорт
import ru.itmentor.spring.boot_security.demo.repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.User;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository; // Предполагаемая зависимость для работы с пользователями
    private final RoleRepository roleRepository; // Предполагаемая зависимость для работы с ролями

    @Autowired
    public UserServicesImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user); // Удален лишний метод
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name); // Предполагается, что этот метод существует
    }

    @Override
    public List<User> findByNameOrEmail(String search) {
        return userRepository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search); // Пример реализации
        // Предполагаем, что в UserRepository существует метод для поиска
    }
}
