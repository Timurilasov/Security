package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repositories.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;  

    @Autowired
    public UserServicesImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null); 
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
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
        return roleRepository.findByName(name); 
    }

    @Override
    public List<User> findByNameOrEmail(String search) {
        return userRepository.findByUsernameContainingOrEmailContaining(search, search);
    }

    @Transactional
    @Override
    public User createUser(User user) {
     
        userRepository.save(user); 
        return user;
    }

    @Override
    public User readUser(Long id) {
        return getUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User master(Long id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser); 
        return updatedUser;
    }

    @Override
    public List<User> getUsersAndRoles() {
        return userRepository.listUsersAndRoles(); 
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user; 
    }
}

