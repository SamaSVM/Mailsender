package com.example.mailsender.service;

import com.example.mailsender.domain.User;
import com.example.mailsender.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
