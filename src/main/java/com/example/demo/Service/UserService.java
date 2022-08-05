package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Model.enummeration.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
