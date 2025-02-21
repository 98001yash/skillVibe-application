package com.company.skillVibe.quiz_service.repository;

import com.company.skillVibe.quiz_service.entities.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByUserId(Long userId);
}
