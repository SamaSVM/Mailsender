package com.example.mailsender.service;

import com.example.mailsender.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final CountService countService;

    public EmailSenderService(JavaMailSender mailSender, UserService userService, CountService countService) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.countService = countService;
    }

    @Value("${mailSender.username}")
    String username;

    public void sendMail(User user) {
        String body = "Ім'я користувача: " + user.getUsername() + "\nДата та час створення: " + user.getCreatedOn();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(user.getEmail());
        message.setText(body);
        message.setSubject("Вітання!");
        mailSender.send(message);
    }

    public void sendMailForAll() {
        userService.getAll().forEach(user -> {
            sendMail(user);
            userService.setSendMailDate(user.getId());
            countService.plusCronCount(user.getCount().getId());
        });
    }
}
