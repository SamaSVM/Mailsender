package com.example.mailsender.controllers;

import com.example.mailsender.domain.Cron;
import com.example.mailsender.service.CronService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crons")
public class CronController extends AbstractController<Cron, CronService> {
    public CronController(CronService service) {
        super(service);
    }
}
