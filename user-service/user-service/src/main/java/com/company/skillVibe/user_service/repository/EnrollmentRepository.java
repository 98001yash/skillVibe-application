package com.company.skillVibe.user_service.repository;

import com.company.skillVibe.user_service.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    List<Enrollment> findByUserId(Long userId);
    Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);
}
