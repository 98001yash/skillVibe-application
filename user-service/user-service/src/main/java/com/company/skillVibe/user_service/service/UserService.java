package com.company.skillVibe.user_service.service;

import com.company.skillVibe.user_service.client.AuthServiceClient;
import com.company.skillVibe.user_service.dtos.UserRequest;
import com.company.skillVibe.user_service.dtos.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final AuthServiceClient authServiceClient;

    public UserResponse getUserProfile(String email){
        log.info("Fetching user profile for email: {}", email);
        return authServiceClient.getUserByEmail(email);
    }

    public UserResponse updateUserProfile(UserRequest userRequest){
        log.info("Updating profile for user: {}", userRequest.getEmail());
        return authServiceClient.updateUser(userRequest);
    }

    public String getUserRole(String email){
        log.info("Fetching role for email: {}", email);
        UserResponse user = authServiceClient.getUserByEmail(email);
        return user.getRole();
    }

    // New method to get user by ID
    public UserResponse getUserById(Long userId) {
        log.info("Fetching user profile for ID: {}", userId);
        return authServiceClient.getUserById(userId);
    }
}
