package com.company.skillVibe.user_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDashboardResponse {
    private UserResponse user;
    private List<EnrollmentDto> enrollments;
    private List<QuizResultDto> quizResults;
}
