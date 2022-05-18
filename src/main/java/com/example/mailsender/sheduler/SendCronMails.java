package com.example.mailsender.sheduler;

import com.example.mailsender.service.EmailSenderService;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
public class SendCronMails implements Runnable {

    private final EmailSenderService emailSenderService;

    public SendCronMails(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void run() {
        emailSenderService.sendMailForAll();
    }
}