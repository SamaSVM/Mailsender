package com.example.mailsender.domain;

import com.cronutils.model.CronType;
import com.example.mailsender.domain.parents.Resource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Table(name = "crons")
public class Cron extends Resource {
    @com.cronutils.validation.Cron(type = CronType.SPRING, message = "Expression field must match the format - cron!")
    @NotEmpty(message = "Expression field must not be empty")
    @Column(name = "expression")
    private String expression;
}
