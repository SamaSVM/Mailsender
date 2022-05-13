package com.example.mailsender.repository;

import com.example.mailsender.domain.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonRepository<E extends Domain> extends JpaRepository<E, Integer> {
}
