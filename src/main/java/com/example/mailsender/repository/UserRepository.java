package com.example.mailsender.repository;

import com.example.mailsender.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<User> {
}
