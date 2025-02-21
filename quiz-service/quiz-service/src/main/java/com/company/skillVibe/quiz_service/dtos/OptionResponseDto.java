package com.company.skillVibe.quiz_service.dtos;

import lombok.Data;

@Data
public class OptionResponseDto {
    private Long id;
    private String optionText;
    private Boolean isCorrect;
}
