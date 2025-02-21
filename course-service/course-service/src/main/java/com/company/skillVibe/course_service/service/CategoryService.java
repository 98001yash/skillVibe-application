package com.company.skillVibe.course_service.service;


import com.company.skillVibe.course_service.dtos.CategoryDto;
import com.company.skillVibe.course_service.entities.Category;
import com.company.skillVibe.course_service.exceptions.ResourceNotFoundException;
import com.company.skillVibe.course_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {


    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;



    public CategoryDto createCategory(CategoryDto categoryDto){
        log.info("Creating a new Category: {}", categoryDto.getName());
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        log.info("Category created with ID: {}",savedCategory.getId());
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    public CategoryDto getCategoryById(Long id){
        log.info("Fetching category with ID: {}",id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category  not found with id: "+id));
        return modelMapper.map(category, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories(){
        log.info("Getting all the categories");
        return categoryRepository.findAll().stream()
                .map(category->modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }



    public CategoryDto updateCategory(Long id, CategoryDto categoryDto){
        log.info("updating category with id: {}",id);
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with ID: "+id));

        existingCategory.setName(categoryDto.getName());
        existingCategory.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    public void deleteCategory(Long id){
        log.info("Deleting category with id: {}",id);
        categoryRepository.deleteById(id);
    }
}
