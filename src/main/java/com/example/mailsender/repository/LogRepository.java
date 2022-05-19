package com.example.mailsender.repository;

import com.example.mailsender.domain.Log;
import com.example.mailsender.domain.User;
import com.example.mailsender.domain.enums.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends CommonRepository<Log> {
    List<Log> findAllByUserAndTypeOrderByCreatedOnAsc(User user, Type type);

    void deleteByUser(User user);
}
