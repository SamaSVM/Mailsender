package com.example.mailsender.domain;

import com.example.mailsender.domain.parents.Resource;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends Resource {
    @NotEmpty(message = "Username field must not be empty")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Email field must not be empty")
    @Email(message = "Email field must be a well-formed email address")
    @Column(name = "email")
    private String email;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "count_id", referencedColumnName = "id")
    private Count count;

    @Column(name = "first")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime first;

    @Column(name = "last")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime last;

    @Override
    public String toString() {
        return "{\"username\":\"" + username
                + "\", \"email\":\"" + email
                + "\", " + count
                + ", \"first\": \"" + first
                + "\", \"last\": \"" + last
                + "\"}";
    }
}
