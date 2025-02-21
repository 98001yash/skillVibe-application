package com.company.skillVibe.user_service.client;

import com.company.skillVibe.user_service.dtos.QuizResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "quiz-service")
public interface QuizServiceClient {
    @GetMapping("/quiz/user/{userId}")
    List<QuizResultDto> getUserQuizResults(@PathVariable Long userId);
}
