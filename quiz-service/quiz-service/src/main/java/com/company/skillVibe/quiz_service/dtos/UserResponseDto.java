package com.company.skillVibe.quiz_service.dtos;


import lombok.Data;

@Data
public class UserResponseDto {


    private Long id;
    private String name;
    private String email;
    private String role;
}
