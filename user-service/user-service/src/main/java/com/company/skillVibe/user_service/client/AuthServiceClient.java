package com.company.skillVibe.user_service.client;


import com.company.skillVibe.user_service.dtos.UserRequest;
import com.company.skillVibe.user_service.dtos.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="auth-service")
public interface AuthServiceClient {

    @GetMapping("/auth/user/email/{email}")
    UserResponse getUserByEmail(@PathVariable String email);

    @PutMapping("/auth/user")
    UserResponse updateUser(@RequestBody UserRequest userRequest);

    // Fix incorrect path: it should match AuthController
    @GetMapping("/auth/user/id/{userId}")  // Corrected path
    UserResponse getUserById(@PathVariable Long userId);
}
