papackage ru.itmentor.spring.boot_security.demo.services;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServices {

    User findByUsername(String username); // Найти пользователя по имени

    User getUserById(Long id); // Получить пользователя по ID

    User save(User user); // Сохранить или обновить пользователя

    void deleteUserById(Long id); // Удалить пользователя по ID

    List<User> getAllUsers(); // Получить всех пользователей

    Role findByName(String name); // Найти роль по имени

    List<User> findByNameOrEmail(String search); // Найти пользователей по имени или email
}

