package com.company.skillVibe.course_service.dtos;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private Long id;
    private String title;
    private String description;
    private String duration;
    private CategoryDto category;
    private List<ModuleDto> modules;
}
