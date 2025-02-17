package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

        private final UserRepository userRepository;

        @Autowired
        public UserDetailsServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        @Transactional
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            System.out.println("Searching for user: " + username); 
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            System.out.println("User found: " + user); 
            return user; 
        }
    }
