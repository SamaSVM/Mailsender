package com.example.mailsender.domain;

import com.example.mailsender.domain.parents.Domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "counts")
public class Count extends Domain {
    @Column(name = "rest")
    private Integer rest = 0;

    @Column(name = "cron")
    private Integer cron = 0;

    @Override
    public String toString() {
        return "\"count\":{" +
                "\"rest\": " + rest +
                ", \"cron\": " + cron +
                "}";
    }
}
