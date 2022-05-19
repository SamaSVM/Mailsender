package com.example.mailsender.service;

import com.example.mailsender.domain.User;
import com.example.mailsender.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public List<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User update(User entity) {
        User user = getById(entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        return super.update(user);
    }
}
