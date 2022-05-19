package com.example.mailsender.domain;

import com.example.mailsender.domain.enums.Type;
import com.example.mailsender.domain.parents.Domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "logs")
public class Log extends Domain {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "type")
    private Type type;
}
