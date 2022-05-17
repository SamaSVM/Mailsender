package com.example.mailsender.service;

import com.example.mailsender.domain.Count;
import com.example.mailsender.repository.CountRepository;
import org.springframework.stereotype.Service;

@Service
public class CountService extends AbstractService<Count, CountRepository> {
    public CountService(CountRepository repository) {
        super(repository);
    }

    public void plusRestCount(Integer id) {
        Count count = getById(id);
        count.setRest(count.getRest() + 1);
        update(count);
    }

    public void plusCronCount(Integer id) {
        Count count = getById(id);
        count.setCron(count.getCron() + 1);
        update(count);
    }
}
