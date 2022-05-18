package com.example.mailsender.service;

import com.example.mailsender.domain.Cron;
import com.example.mailsender.repository.CronRepository;
import com.example.mailsender.sheduler.SendCronMails;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class CronService extends AbstractService<Cron, CronRepository> {
    public CronService(CronRepository repository, EmailSenderService emailSenderService,
                       ThreadPoolTaskScheduler taskScheduler) {
        super(repository);
        this.emailSenderService = emailSenderService;
        this.taskScheduler = taskScheduler;
    }

    private final EmailSenderService emailSenderService;

    private final ThreadPoolTaskScheduler taskScheduler;

    private final Map<Integer, ScheduledFuture<?>> tasks = new HashMap<>();

    @Override
    public Cron save(Cron entity) {
        Cron cron = super.save(entity);
        addTask(cron);
        return cron;
    }

    @Override
    public void delete(Integer id) {
        deleteTask(id);
        super.delete(id);
    }

    private void addTask(Cron entity) {
        CronTrigger cronTrigger = new CronTrigger(entity.getExpression());
        tasks.put(entity.getId(), taskScheduler.schedule(new SendCronMails(emailSenderService), cronTrigger));
    }

    private void deleteTask(Integer id) {
        tasks.remove(id).cancel(true);
    }
}
