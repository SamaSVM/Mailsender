package com.example.mailsender.controllers;

import com.example.mailsender.domain.User;
import com.example.mailsender.domain.enums.Type;
import com.example.mailsender.exception.ResourceNotFoundException;
import com.example.mailsender.service.EmailSenderService;
import com.example.mailsender.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User, UserService> {
    public UserController(UserService service, EmailSenderService senderService) {
        super(service);
        this.senderService = senderService;
    }

    private final EmailSenderService senderService;

    @PostMapping("/{id}/sendmail")
    public ResponseEntity<String> sendMail(@PathVariable Integer id) {
        User user;
        try {
            user = service.getById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("User from id - " + id + " does not exist!");
        }
        senderService.sendMail(user, Type.REST);
        return new ResponseEntity<>("The mail has been sent!", HttpStatus.OK);
    }

    @GetMapping("/{value}")
    public List<User> readByValue(@PathVariable String value) {
        List<User> result = service.findByUsername(value);
        User user = service.findByEmail(value);
        if (user != null) {
            result.add(user);
        }
        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result;
    }
}
