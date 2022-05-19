package com.example.mailsender.controllers;

import com.example.mailsender.domain.parents.Domain;
import com.example.mailsender.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class AbstractController<E extends Domain, S extends CommonService<E>> implements CommonController<E> {
    S service;

    public AbstractController(S service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<E> create(@Valid @RequestBody E resource, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder exceptions = new StringBuilder();
            bindingResult.getAllErrors().forEach(err -> exceptions.append(err.getDefaultMessage()).append("\n"));
            return new ResponseEntity(exceptions, HttpStatus.BAD_REQUEST);
        }
        E result = service.save(resource);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<E> update(
            @PathVariable Integer id, @Valid @RequestBody E resource, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder exceptions = new StringBuilder();
            bindingResult.getAllErrors().forEach(err -> exceptions.append(err.getDefaultMessage()).append("\n"));
            return new ResponseEntity(exceptions, HttpStatus.BAD_REQUEST);
        }
        resource.setId(id);
        E result = service.update(resource);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public HttpStatus delete(@PathVariable Integer id) {
        service.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping
    @Override
    public List<E> readAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return service.getAll(page, size);
    }
}
