package com.company.skillVibe.auth_service.dtos;

import com.company.skillVibe.auth_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Ensures a default constructor is available
@AllArgsConstructor // Ensures a constructor with all fields is generated
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Role role = Role.STUDENT; // Default to STUDENT
}
