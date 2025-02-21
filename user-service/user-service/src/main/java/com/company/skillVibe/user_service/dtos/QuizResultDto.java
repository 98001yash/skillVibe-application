package com.company.skillVibe.user_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDto {
    private Long id;
    private Long userId;
    private Long courseId;
    private int score;
}
