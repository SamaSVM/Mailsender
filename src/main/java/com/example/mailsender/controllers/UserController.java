package com.example.mailsender.controllers;

import com.example.mailsender.domain.User;
import com.example.mailsender.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User, UserService> {
    public UserController(UserService service) {
        super(service);
    }
}
