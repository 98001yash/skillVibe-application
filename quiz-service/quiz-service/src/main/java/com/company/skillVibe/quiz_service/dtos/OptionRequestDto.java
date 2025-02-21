package com.company.skillVibe.quiz_service.dtos;

import lombok.Data;

@Data
public class OptionRequestDto {
    private String optionText;
    private Boolean isCorrect;  // Make sure this is Boolean and not boolean
}
