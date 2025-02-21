package com.company.skillVibe.quiz_service.dtos;

import lombok.Data;
import java.util.List;

@Data
public class QuestionRequestDto {
    private String questionText;
    private String questionType;
    private List<OptionRequestDto> options;
}
