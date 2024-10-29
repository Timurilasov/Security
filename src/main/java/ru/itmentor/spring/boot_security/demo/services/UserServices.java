papackage ru.itmentor.spring.boot_security.demo.services;

import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServices {

    User findByUsername(String username);

    User getUserById(Long id);

    User save(User user);

    void deleteUserById(Long id);

    List<User> getAllUsers(); 

    Role findByName(String name); 

    List<User> findByNameOrEmail(String search);

