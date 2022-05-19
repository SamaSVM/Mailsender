package com.example.mailsender.service;

import com.example.mailsender.domain.User;
import com.example.mailsender.domain.enums.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    private final JavaMailSender mailSender;
    private final UserService userService;
    private final LogService logService;

    public EmailSenderService(JavaMailSender mailSender, UserService userService, LogService logService) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.logService = logService;
    }

    @Value("${mailSender.username}")
    String username;

    public void sendMail(User user, Type type) {
        String body = "Ім'я користувача: " + user.getUsername() + "\nДата та час створення: " + user.getCreatedOn();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(user.getEmail());
        message.setText(body);
        message.setSubject("Вітання!");
        mailSender.send(message);
        logService.save(user, type);
    }

    public void sendMailForAll() {
        userService.getAll().forEach(user -> sendMail(user, Type.CRON));
    }
}
