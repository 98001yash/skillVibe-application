package com.company.skillVibe.user_service.client;


import com.company.skillVibe.user_service.dtos.CourseRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "course-service", path= "/course")
public interface CourseClient {

    @GetMapping("/{id}")
    CourseRequestDto getCourseById(@PathVariable("id") Long id);
}
