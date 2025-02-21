package com.company.skillVibe.course_service.repository;

import com.company.skillVibe.course_service.entities.Category;
import com.company.skillVibe.course_service.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByCategory(Category category);

}
