package com.company.skillVibe.course_service.repository;

import com.company.skillVibe.course_service.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
