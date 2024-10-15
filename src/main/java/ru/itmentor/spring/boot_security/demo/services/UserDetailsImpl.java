package ru.itmentor.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Преобразуем роли пользователя в GrantedAuthority
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Проверка на срок действия аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Проверка на заблокирован ли аккаунт
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Проверка на срок действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        return true; // Проверка на активность аккаунта
    }

    public User getUser() {
        return user; // Метод для получения пользователя
    }
}
