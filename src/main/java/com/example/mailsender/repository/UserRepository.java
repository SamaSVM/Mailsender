package com.example.mailsender.repository;

import com.example.mailsender.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CommonRepository<User> {
    List<User> findByUsername(String username);

    User findByEmail(String email);
}
