package com.example.demo.Service.Implementation;

import com.example.demo.Model.User;
import com.example.demo.Model.exception.NotGoodException;
import com.example.demo.Model.exception.PasswordsDoNotMatchException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new PasswordsDoNotMatchException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(PasswordsDoNotMatchException::new);
    }

}
