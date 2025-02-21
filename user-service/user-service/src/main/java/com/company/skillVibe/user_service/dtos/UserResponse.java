package com.company.skillVibe.user_service.dtos;


import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String role;
}
