package com.company.skillVibe.course_service.controller;

import com.company.skillVibe.course_service.dtos.CourseRequestDto;
import com.company.skillVibe.course_service.dtos.CourseResponseDto;
import com.company.skillVibe.course_service.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        log.info("Received request to create a new course: {}", courseRequestDto);
        CourseResponseDto courseResponse = courseService.createCourse(courseRequestDto);
        log.info("Course created successfully: {}", courseResponse);
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        log.info("Received request to fetch course by ID: {}", id);
        CourseResponseDto courseResponse = courseService.getCourseById(id);
        log.info("Fetched course details: {}", courseResponse);
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        log.info("Received request to fetch all courses");
        List<CourseResponseDto> courses = courseService.getAllCourses();
        log.info("Fetched all courses: {}", courses);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Long id, @RequestBody CourseRequestDto courseRequestDto) {
        log.info("Received request to update course with ID: {} and details: {}", id, courseRequestDto);
        CourseResponseDto updatedCourse = courseService.updateCourse(id, courseRequestDto);
        log.info("Course updated successfully: {}", updatedCourse);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("Received request to delete course by ID: {}", id);
        courseService.deleteCourse(id);
        log.info("Course deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/by/{categoryId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByCategory(@PathVariable Long categoryId) {
        List<CourseResponseDto> courses = courseService.getCoursesByCategory(categoryId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
