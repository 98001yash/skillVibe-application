package com.company.skillVibe.quiz_service.controller;


import com.company.skillVibe.quiz_service.dtos.QuizRequestDto;
import com.company.skillVibe.quiz_service.dtos.QuizResponseDto;
import com.company.skillVibe.quiz_service.dtos.QuizResultDto;
import com.company.skillVibe.quiz_service.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponseDto> createQuiz(@Valid @RequestBody QuizRequestDto quizRequestDto){
        log.info("Received request to create quiz: {}",quizRequestDto);
        QuizResponseDto quizResponse = quizService.createQuiz(quizRequestDto);
        log.info("Quiz created successfully: {}",quizResponse);
        return ResponseEntity.ok(quizResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponseDto> getQuizById(@PathVariable Long id){
        log.info("Received request to fetch Quiz by ID: {}",id);
        QuizResponseDto quizResponse = quizService.getQuizById(id);
        log.info("Fetched quiz details: {}",quizResponse);
        return ResponseEntity.ok(quizResponse);
    }

    @GetMapping
    public ResponseEntity<List<QuizResponseDto>> getAllQuizzes(){
        log.info("Received request tp fetch all quizzes");
        List<QuizResponseDto> quizResponses = quizService.getAllQuizzes();
        log.info("Fetched all quizzes: {}",quizResponses);
        return ResponseEntity.ok(quizResponses);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id){
        log.info("Received request tp delete quiz by ID:{}",id);
        quizService.deleteQuiz(id);
        log.info("Quiz deleted successfully with ID:{}",id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<QuizResponseDto>> getQuizzesByCourse(@PathVariable Long courseId) {
        List<QuizResponseDto> quizzes = quizService.getQuizzesByCourse(courseId);
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuizResultDto>> getUserQuizResults(@PathVariable Long userId) {
        log.info("Fetching quiz results for user ID: {}", userId);
        List<QuizResultDto> quizResults = quizService.getUserQuizResults(userId);
        log.info("Fetched {} quiz results for user ID: {}", quizResults.size(), userId);
        return ResponseEntity.ok(quizResults);
    }
}
