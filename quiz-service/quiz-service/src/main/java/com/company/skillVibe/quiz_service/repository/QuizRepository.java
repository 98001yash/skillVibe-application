package com.company.skillVibe.quiz_service.repository;

import com.company.skillVibe.quiz_service.entities.Quiz;
import com.company.skillVibe.quiz_service.entities.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuizRepository extends JpaRepository<Quiz,Long> {


    List<Quiz> findByCourseId(Long courseId);


}
