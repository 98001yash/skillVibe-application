package com.company.skillVibe.quiz_service.dtos;

import lombok.Data;
import java.util.List;

@Data
public class QuizResponseDto {
    private Long id;
    private String title;
    private String description;
    private int duration;
    private List<QuestionResponseDto> questions;
}
