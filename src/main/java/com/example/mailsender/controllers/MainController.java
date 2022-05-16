package com.example.mailsender.controllers;

import com.example.mailsender.domain.User;
import com.example.mailsender.forms.UserForm;
import com.example.mailsender.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    private final UserService userService;

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .findAndRegisterModules();

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/create"})
    public String showAddUserPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "createUser";
    }

    @PostMapping(value = {"/create"})
    public String saveUser(Model model, @ModelAttribute("userForm") UserForm userForm) {
        if (userForm.getUsername().length() == 0 || userForm.getEmail().length() == 0) {
            model.addAttribute("errorMessage", "Username & Email is required!");
            return "createUser";
        }

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());

        try {
            userService.save(user);
            return "redirect:/userList";
        } catch (RuntimeException e) {
            model.addAttribute
                    ("errorMessage", "Email is already in use or there is an exception!");
            return "createUser";
        }
    }

    @GetMapping(value = {"/update"})
    public String showUpdateUserPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "updateUser";
    }

    @PostMapping(value = {"/update"})
    public String updateUser(Model model, @ModelAttribute("userForm") UserForm userForm) {
        if (userForm.getUsername().length() == 0 || userForm.getEmail().length() == 0) {
            model.addAttribute("errorMessage", "Id, Username & Email is required!");
            return "updateUser";
        }

        try {
            userService.getById(userForm.getId());
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "User not found!");
            return "updateUser";
        }

        User user = new User();
        user.setId(userForm.getId());
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());

        try {
            userService.update(user);
            return "redirect:/userList";
        } catch (RuntimeException e) {
            model.addAttribute
                    ("errorMessage", "Email is already in use or there is an exception!");
            return "updateUser";
        }
    }

    @GetMapping(value = {"/delete"})
    public String showDeleteUserPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "deleteUser";
    }

    @PostMapping(value = {"/delete"})
    public String deleteUser(Model model, @ModelAttribute("userForm") UserForm userForm) {
        try {
            userService.delete(userForm.getId());
            return "redirect:/userList";
        } catch (RuntimeException e) {
            model.addAttribute
                    ("errorMessage", "User with id - " + userForm.getId() + " not found!");
            return "deleteUser";
        }
    }

    @GetMapping(value = {"/searchByUsername"})
    public String showSearchByUsernameUserPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "searchByUsernameUser";
    }

    @PostMapping(value = {"/searchByUsername"})
    public String searchByUsernameUser(Model model, @ModelAttribute("userForm") UserForm userForm) {
        if (userForm.getUsername().length() == 0) {
            model.addAttribute("errorMessage", "Username is required!");
            return "searchByUsernameUser";
        }

        try {
            User user = userService.findByUsername(userForm.getUsername());
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(user.toString()));
            model.addAttribute("user", json);
        } catch (JsonProcessingException | RuntimeException e) {
            model.addAttribute("errorMessage", "User not found!");
            return "searchByUsernameUser";
        }
        return "user";
    }

    @GetMapping(value = {"/searchByEmail"})
    public String showSearchByEmailUserPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "searchByEmailUser";
    }

    @PostMapping(value = {"/searchByEmail"})
    public String searchByEmailUser(Model model, @ModelAttribute("userForm") UserForm userForm) {
        if (userForm.getEmail().length() == 0) {
            model.addAttribute("errorMessage", "Email is required!");
            return "searchByEmailUser";
        }

        try {
            User user = userService.findByEmail(userForm.getEmail());
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(user.toString()));
            model.addAttribute("user", json);
        } catch (JsonProcessingException | RuntimeException e) {
            model.addAttribute("errorMessage", "User not found!");
            return "searchByEmailUser";
        }
        return "user";
    }

    @GetMapping(value = {"/userList"})
    public String userList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }
}
