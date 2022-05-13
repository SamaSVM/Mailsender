package com.example.mailsender.controllers;

import com.example.mailsender.domain.Domain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommonController<E extends Domain> {

    ResponseEntity<E> create(E resource);

    ResponseEntity<E> update(Integer id, E resource);

    HttpStatus delete(Integer id);

    ResponseEntity<E> readById(Integer id);

    List<E> readAll();
}
