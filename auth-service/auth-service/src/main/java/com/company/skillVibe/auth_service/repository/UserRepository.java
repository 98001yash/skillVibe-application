package com.company.skillVibe.auth_service.repository;

import com.company.skillVibe.auth_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<User> findById(Long id);

}
