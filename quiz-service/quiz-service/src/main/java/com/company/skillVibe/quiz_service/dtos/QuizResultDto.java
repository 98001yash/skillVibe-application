package com.company.skillVibe.quiz_service.dtos;

import lombok.Data;

@Data
public class QuizResultDto {
    private Long id;
    private Long userId;
    private Long courseId;
    private int score;
}
