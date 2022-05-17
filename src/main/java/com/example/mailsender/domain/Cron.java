package com.example.mailsender.domain;

import com.example.mailsender.domain.parents.Resource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "crons")
public class Cron extends Resource {
    @NotNull
    @NotEmpty
    @Column(name = "expression")
    private String expression;
}
