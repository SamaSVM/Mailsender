package com.example.mailsender.controllers;

import com.example.mailsender.domain.dao.Report;
import com.example.mailsender.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/reports")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping
    public List<Report> getReport(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return service.getReportsForAllPage(page, size);
    }
}
