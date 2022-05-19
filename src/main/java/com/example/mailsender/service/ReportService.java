package com.example.mailsender.service;

import com.example.mailsender.domain.Log;
import com.example.mailsender.domain.User;
import com.example.mailsender.domain.dao.Count;
import com.example.mailsender.domain.dao.Report;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReportService {
    private final UserService userService;
    private final LogService logService;

    public ReportService(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    public Report getReportForUser(User user) {
        Report report = new Report();
        report.setUsername(user.getUsername());
        report.setEmail(user.getEmail());

        List<Log> crudLog = logService.getAllCronLogForUser(user);
        List<Log> restLog = logService.getAllRestLogForUser(user);
        Count count = new Count();
        count.setCron(crudLog.size());
        count.setRest(restLog.size());
        report.setCount(count);

        if (!crudLog.isEmpty()) {
            report.setFirst(crudLog.get(0).getCreatedOn());
            report.setLast(crudLog.get(crudLog.size() - 1).getCreatedOn());
        }

        if (!restLog.isEmpty()) {
            if (report.getFirst() == null || report.getFirst().isAfter(restLog.get(0).getCreatedOn())) {
                report.setFirst(restLog.get(0).getCreatedOn());
            }
            if (report.getLast() == null || report.getLast().isBefore(restLog.get(restLog.size() - 1).getCreatedOn())) {
                report.setLast(restLog.get(restLog.size() - 1).getCreatedOn());
            }
        }
        return report;
    }

    public List<Report> getReportsForAll() {
        List<Report> result = new ArrayList<>();
        List<User> users = userService.getAll();

        users.forEach(user -> result.add(getReportForUser(user)));
        Collections.sort(result);
        return result;
    }

    public List<Report> getReportsForAllPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Report> reportList = getReportsForAll();
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), reportList.size());
        try {
            return new PageImpl<>(reportList.subList(start, end), pageRequest, reportList.size()).getContent();
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }
}
