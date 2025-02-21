package com.company.skillVibe.quiz_service.repository;

import com.company.skillVibe.quiz_service.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
}
