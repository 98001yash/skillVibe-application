package com.company.skillVibe.course_service.client;

import com.company.skillVibe.course_service.dtos.QuizResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "quiz-service")
public interface QuizClient {

    @GetMapping("/quizzes/course/{courseId}")
    List<QuizResponseDto> getQuizzesByCourse(@PathVariable("courseId") Long courseId);
}
