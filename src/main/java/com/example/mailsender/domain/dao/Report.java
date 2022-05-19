package com.example.mailsender.domain.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Report implements Comparable<Report> {
    private String username;

    private String email;

    private Count count;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime first;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime last;

    @Override
    public int compareTo(Report o) {
        int oSum = o.getCount().getCron() + o.getCount().getRest();
        int sum = count.getCron() + count.getRest();
        return Integer.compare(oSum, sum);
    }
}
