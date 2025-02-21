package com.company.skillVibe.user_service.entities;


import com.company.skillVibe.user_service.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long courseId;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;
    private Double progress;
}
