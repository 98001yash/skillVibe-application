package com.company.skillVibe.user_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
