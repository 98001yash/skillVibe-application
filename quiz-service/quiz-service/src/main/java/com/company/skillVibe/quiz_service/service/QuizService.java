package com.company.skillVibe.quiz_service.service;


import com.company.skillVibe.quiz_service.dtos.QuizRequestDto;
import com.company.skillVibe.quiz_service.dtos.QuizResponseDto;
import com.company.skillVibe.quiz_service.dtos.QuizResultDto;
import com.company.skillVibe.quiz_service.entities.Option;
import com.company.skillVibe.quiz_service.entities.Question;
import com.company.skillVibe.quiz_service.entities.Quiz;
import com.company.skillVibe.quiz_service.entities.QuizResult;
import com.company.skillVibe.quiz_service.exceptions.ResourceNotFoundException;


import com.company.skillVibe.quiz_service.repository.QuizRepository;
import com.company.skillVibe.quiz_service.repository.QuizResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {

   private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    private final QuizResultRepository quizResultRepository;

    /**
     * Create a new Quiz
     */
    @Transactional
    public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
        log.info("Creating a new Quiz: {}", quizRequestDto);

        // Map QuizRequestDto to Quiz entity
        Quiz quiz = modelMapper.map(quizRequestDto, Quiz.class);

        // Establish Relationships
        quiz.getQuestions().forEach(question -> {
            question.setQuiz(quiz);
            question.getOptions().forEach(option -> option.setQuestion(question));
        });

        // Persist Quiz
        Quiz savedQuiz = quizRepository.save(quiz);

        log.info("Quiz created successfully with ID: {}", savedQuiz.getId());

        // Map saved Quiz to QuizResponseDto
        return modelMapper.map(savedQuiz, QuizResponseDto.class);
    }

    /**
     * Get all Quizzes
     */
    public List<QuizResponseDto> getAllQuizzes() {
        log.info("Fetching all quizzes");

        List<Quiz> quizzes = quizRepository.findAll();

        return quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizResponseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Get Quiz by ID
     */
    public QuizResponseDto getQuizById(Long id) {
        log.info("Fetching Quiz by ID: {}", id);

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", id));

        return modelMapper.map(quiz, QuizResponseDto.class);
    }

    /**
     * Update an existing Quiz
     */
    @Transactional
    public QuizResponseDto updateQuiz(Long id, QuizRequestDto quizRequestDto) {
        log.info("Updating Quiz with ID: {}", id);

        // Fetch existing Quiz
        Quiz existingQuiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", id));

        // Update Quiz details
        existingQuiz.setTitle(quizRequestDto.getTitle());
        existingQuiz.setDescription(quizRequestDto.getDescription());
        existingQuiz.setDuration(quizRequestDto.getDuration());

        // Update Questions and Options
        List<Question> updatedQuestions = quizRequestDto.getQuestions().stream()
                .map(qDto -> {
                    Question question = modelMapper.map(qDto, Question.class);
                    question.setQuiz(existingQuiz);

                    List<Option> options = qDto.getOptions().stream()
                            .map(oDto -> modelMapper.map(oDto, Option.class))
                            .collect(Collectors.toList());
                    question.setOptions(options);

                    return question;
                }).collect(Collectors.toList());
        existingQuiz.setQuestions(updatedQuestions);

        // Save updated Quiz
        Quiz updatedQuiz = quizRepository.save(existingQuiz);
        log.info("Quiz updated successfully with ID: {}", updatedQuiz.getId());

        return modelMapper.map(updatedQuiz, QuizResponseDto.class);
    }

    @Transactional
    public void deleteQuiz(Long id) {
        log.info("Deleting Quiz with ID: {}", id);
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz", "id", id));
        quizRepository.delete(quiz);
        log.info("Quiz deleted successfully with ID: {}", id);
    }

    public List<QuizResponseDto> getQuizzesByCourse(Long courseId) {
        List<Quiz> quizzes = quizRepository.findByCourseId(courseId);
        return quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<QuizResultDto> getUserQuizResults(Long userId) {
        log.info("Fetching quiz results from database for user ID: {}", userId);

        List<QuizResult> quizResults = quizResultRepository.findByUserId(userId);

        log.info("Found {} quiz results for user ID: {}", quizResults.size(), userId);

        return quizResults.stream()
                .map(result -> modelMapper.map(result, QuizResultDto.class))
                .collect(Collectors.toList());
    }
}
