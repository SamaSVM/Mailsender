package com.example.mailsender.domain.parents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Resource extends Domain {
    @JsonIgnore
    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();
}
