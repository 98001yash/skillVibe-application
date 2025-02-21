package com.company.skillVibe.course_service.dtos;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {
    private String title;
    private String description;
    private String duration;
    private Long categoryId;
    private List<ModuleDto> modules;
}
