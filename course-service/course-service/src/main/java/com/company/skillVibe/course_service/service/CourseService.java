package com.company.skillVibe.course_service.service;

import com.company.skillVibe.course_service.dtos.CourseRequestDto;
import com.company.skillVibe.course_service.dtos.CourseResponseDto;
import com.company.skillVibe.course_service.entities.Category;
import com.company.skillVibe.course_service.entities.Course;
import com.company.skillVibe.course_service.entities.Module;
import com.company.skillVibe.course_service.exceptions.ResourceNotFoundException;
import com.company.skillVibe.course_service.repository.CategoryRepository;
import com.company.skillVibe.course_service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        log.info("Creating new course: {}", courseRequestDto);

        // Fetch category entity from DB
        Category category = categoryRepository.findById(courseRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Create a new Course entity and map fields
        Course newCourse = new Course();
        newCourse.setTitle(courseRequestDto.getTitle());
        newCourse.setDescription(courseRequestDto.getDescription());
        newCourse.setDuration(courseRequestDto.getDuration());
        newCourse.setCategory(category);

        // Convert modules and associate them with the course
        List<Module> modules = courseRequestDto.getModules().stream()
                .map(moduleDto -> modelMapper.map(moduleDto, Module.class))
                .collect(Collectors.toList());

        modules.forEach(module -> {
            module.setCourse(newCourse);
        });

        newCourse.setModules(modules);

        // Save the new course
        Course savedCourse = courseRepository.save(newCourse);

        // Map the saved course entity to response DTO and return
        return modelMapper.map(savedCourse, CourseResponseDto.class);
    }


    public CourseResponseDto getCourseById(Long id) {
        log.info("Fetching course with ID: {}", id);
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return modelMapper.map(course, CourseResponseDto.class);
    }

    public List<CourseResponseDto> getAllCourses() {
        log.info("Fetching all courses");
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        log.info("Updating course with ID: {}", id);

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        Category category = categoryRepository.findById(courseRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        existingCourse.setTitle(courseRequestDto.getTitle());
        existingCourse.setDescription(courseRequestDto.getDescription());
        existingCourse.setDuration(courseRequestDto.getDuration());
        existingCourse.setCategory(category);

        List<Module> modules = courseRequestDto.getModules().stream()
                .map(moduleDto -> modelMapper.map(moduleDto, Module.class))
                .collect(Collectors.toList());

        modules.forEach(module -> module.setCourse(existingCourse));
        existingCourse.setModules(modules);

        courseRepository.save(existingCourse);
        return modelMapper.map(existingCourse, CourseResponseDto.class);
    }

    public void deleteCourse(Long id) {
        log.info("Deleting course with ID: {}", id);
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
        courseRepository.delete(course);
        log.info("Course deleted successfully with ID: {}", id);
    }

    public List<CourseResponseDto> getCoursesByCategory(Long categoryId) {
        // Check if the category exists
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));

        // Fetch courses by category
        List<Course> courses = courseRepository.findByCategory(category);

        // Map courses to response DTOs
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseResponseDto.class))
                .collect(Collectors.toList());
    }
}
