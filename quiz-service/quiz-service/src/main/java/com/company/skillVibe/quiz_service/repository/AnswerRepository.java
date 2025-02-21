package com.company.skillVibe.quiz_service.repository;

import com.company.skillVibe.quiz_service.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
