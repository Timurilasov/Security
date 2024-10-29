package ru.itmentor.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u JOIN FETCH u.roles")
    List<User> listUsersAndRoles();

    List<User> findByUsernameContainingOrEmailContaining(String username, String email);

    User findByEmail(String email); 
}
