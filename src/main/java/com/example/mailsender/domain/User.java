package com.example.mailsender.domain;

import com.example.mailsender.domain.parents.Domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends Domain {
    @NotEmpty(message = "Username field must not be empty")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Email field must not be empty")
    @Email(message = "Email field must be a well-formed email address")
    @Column(name = "email")
    private String email;
}
