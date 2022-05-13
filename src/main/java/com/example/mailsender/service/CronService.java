package com.example.mailsender.service;

import com.example.mailsender.domain.Cron;
import com.example.mailsender.repository.CronRepository;
import org.springframework.stereotype.Service;

@Service
public class CronService extends AbstractService<Cron, CronRepository> {
    public CronService(CronRepository repository) {
        super(repository);
    }
}
