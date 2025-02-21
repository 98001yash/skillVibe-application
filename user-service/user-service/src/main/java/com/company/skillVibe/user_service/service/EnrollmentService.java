package com.company.skillVibe.user_service.service;


import com.company.skillVibe.user_service.client.AuthServiceClient;
import com.company.skillVibe.user_service.client.CourseClient;
import com.company.skillVibe.user_service.dtos.CourseRequestDto;
import com.company.skillVibe.user_service.dtos.EnrollmentDto;
import com.company.skillVibe.user_service.dtos.UserResponse;
import com.company.skillVibe.user_service.entities.Enrollment;
import com.company.skillVibe.user_service.enums.EnrollmentStatus;
import com.company.skillVibe.user_service.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentService {


    private final EnrollmentRepository enrollmentRepository;
    private final CourseClient courseClient;
    private final AuthServiceClient authServiceClient;
    private final ModelMapper modelMapper;


    public EnrollmentDto enrollUser(Long courseId, Long userId) {
        log.info("Checking if course exists in course Service....");
        CourseRequestDto courseDto = courseClient.getCourseById(courseId);
        if (courseDto == null) {
            log.error("Course not found with ID: {}", courseId);
            throw new RuntimeException("Course not found!");
        }

        log.info("Calling AuthService to get user by ID: {}", userId);
        UserResponse user = modelMapper.map(authServiceClient.getUserById(userId), UserResponse.class);
        log.info("Received User from AuthService: {}", user);

        if (user == null) {
            log.error("User not found with ID: {}", userId);
            throw new RuntimeException("User not found....");
        }

        log.info("Enrolling User: {} in course: {}", userId, courseId);
        Enrollment enrollment = Enrollment.builder()
                .userId(userId)
                .courseId(courseId)
                .status(EnrollmentStatus.ACTIVE)
                .progress(0.0)
                .build();
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return modelMapper.map(savedEnrollment, EnrollmentDto.class);
    }


    public List<EnrollmentDto> getUserEnrollment(Long userId){
        log.info("Fetching enrollments for user: {}",userId);
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);
        return enrollments.stream()
                .map(enrollment->modelMapper.map(enrollment, EnrollmentDto.class))
                .collect(Collectors.toList());
    }
}
