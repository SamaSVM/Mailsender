package com.example.mailsender.service;

import java.util.List;

public interface CommonService<E> {
    E save(E entity);

    E update(E entity);

    void delete(Integer id);

    E getById(Integer id);

    List<E> getAll();
}
