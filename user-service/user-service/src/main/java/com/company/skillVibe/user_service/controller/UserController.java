package com.company.skillVibe.user_service.controller;


import com.company.skillVibe.user_service.client.AuthServiceClient;
import com.company.skillVibe.user_service.client.QuizServiceClient;
import com.company.skillVibe.user_service.dtos.*;
import com.company.skillVibe.user_service.service.EnrollmentService;
import com.company.skillVibe.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthServiceClient authServiceClient;
    private final EnrollmentService enrollmentService;
    private final QuizServiceClient quizServiceClient;

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile(@RequestParam String email){
        log.info("Get request for user profile with email: {}",email);
        UserResponse user = userService.getUserProfile(email);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateUserProfile(@RequestBody UserRequest userRequest){
        log.info("PUT request to update profile for user: {}",userRequest.getEmail());
        UserResponse updatedUser = userService.updateUserProfile(userRequest);
        return ResponseEntity.ok(updatedUser);
    }


    @GetMapping("/role")
    public ResponseEntity<String> getUserRole(@RequestParam String email){
        log.info("GET request for user role with email: {}",email);
        String role = userService.getUserRole(email);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/{userId}/dashboard")
    public ResponseEntity<UserDashboardResponse> getUserDashboard(@PathVariable Long userId) {
        log.info("Fetching complete details for user: {}", userId);
        UserResponse user = authServiceClient.getUserById(userId);

        List<EnrollmentDto> enrollments = enrollmentService.getUserEnrollment(userId);

        List<QuizResultDto> quizResults = quizServiceClient.getUserQuizResults(userId);
        UserDashboardResponse userDashboard = new UserDashboardResponse(user, enrollments, quizResults);
        return ResponseEntity.ok(userDashboard);
    }
}
