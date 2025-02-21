package com.company.skillVibe.media_service.repository;

import com.company.skillVibe.media_service.entities.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<MediaFile,Long> {
    Optional<MediaFile> findByCourseId(Long courseId);
}
