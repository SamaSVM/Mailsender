package com.example.mailsender.service;

import com.example.mailsender.domain.Count;
import com.example.mailsender.domain.User;
import com.example.mailsender.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    private final CountService countService;

    public UserService(UserRepository repository, CountService countService) {
        super(repository);
        this.countService = countService;
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User save(User entity) {
        entity.setCount(countService.save(new Count()));
        return super.save(entity);
    }

    public void setSendMailDate(Integer id) {
        User user = getById(id);
        if (user.getFirst() == null) {
            user.setFirst(LocalDateTime.now());
        }
        user.setLast(LocalDateTime.now());
        update(user);
    }
}
