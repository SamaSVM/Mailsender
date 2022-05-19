package com.example.mailsender.service;

import com.example.mailsender.domain.Log;
import com.example.mailsender.domain.User;
import com.example.mailsender.domain.enums.Type;
import com.example.mailsender.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService extends AbstractService<Log, LogRepository> {
    public LogService(LogRepository repository) {
        super(repository);
    }

    public Log save(User user, Type type) {
        Log log = new Log();
        log.setUser(user);
        log.setType(type);
        return super.save(log);
    }


    public List<Log> getAllRestLogForUser(User user) {
        return repository.findAllByUserAndTypeOrderByCreatedOnAsc(user, Type.REST);
    }

    public List<Log> getAllCronLogForUser(User user) {
        return repository.findAllByUserAndTypeOrderByCreatedOnAsc(user, Type.CRON);
    }

}
