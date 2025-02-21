package com.company.skillVibe.user_service.dtos;


import lombok.Data;

@Data
public class EnrollmentDto {

    private Long id;
    private Long userId;
    private Long courseId;
    private String status;
    private double progress;
}
