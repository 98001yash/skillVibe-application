package com.company.skillVibe.quiz_service.repository;

import com.company.skillVibe.quiz_service.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
