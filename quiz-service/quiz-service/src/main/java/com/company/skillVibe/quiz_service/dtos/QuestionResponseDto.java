package com.company.skillVibe.quiz_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponseDto {

    private Long id;
    private String questionText;
    private String questionType;
    private List<OptionResponseDto> options;
}
