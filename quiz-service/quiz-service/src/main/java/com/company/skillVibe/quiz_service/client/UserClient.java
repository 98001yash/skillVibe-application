package com.company.skillVibe.quiz_service.client;


import com.company.skillVibe.quiz_service.dtos.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/users/{userId}")
    UserResponseDto getUserById(@PathVariable("userId") Long userId);
}
