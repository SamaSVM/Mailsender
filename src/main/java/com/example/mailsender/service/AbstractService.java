package com.example.mailsender.service;

import com.example.mailsender.domain.parents.Domain;
import com.example.mailsender.exception.ResourceNotFoundException;
import com.example.mailsender.repository.CommonRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class AbstractService<E extends Domain, R extends CommonRepository<E>> implements CommonService<E> {
    R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public E update(E entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public E getById(Integer id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public List<E> getAll(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("createdOn").descending())).getContent();
    }
}
