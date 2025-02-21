package com.company.skillVibe.user_service.controller;


import com.company.skillVibe.user_service.dtos.EnrollmentDto;
import com.company.skillVibe.user_service.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<EnrollmentDto> enrollUser(@RequestBody EnrollmentDto enrollmentDto) {
        log.info("Received request to enroll user: {} in Course: {}", enrollmentDto.getUserId(), enrollmentDto.getCourseId());
        return ResponseEntity.ok(enrollmentService.enrollUser(enrollmentDto.getUserId(), enrollmentDto.getCourseId()));
    }



    @GetMapping("/{userId}")
    public ResponseEntity<List<EnrollmentDto>> getUserEnrollments(@PathVariable Long userId){
        log.info("Fetching enrolled courses for user: {}",userId);
        return ResponseEntity.ok(enrollmentService.getUserEnrollment(userId));
    }
}
