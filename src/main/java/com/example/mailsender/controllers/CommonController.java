package com.example.mailsender.controllers;

import com.example.mailsender.domain.parents.Domain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CommonController<E extends Domain> {

    ResponseEntity<E> create(E resource, BindingResult bindingResult);

    ResponseEntity<E> update(Integer id, E resource, BindingResult bindingResult);

    HttpStatus delete(Integer id);

    ResponseEntity<E> readById(Integer id);

    List<E> readAll();
}
