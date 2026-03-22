package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.CategoryDto;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    CategoryDto createCategory(CategoryDto dto);
    CategoryDto updateCategory(Long id , CategoryDto dto );
    CategoryDto getCategory(Long id);
    List<CategoryDto> getAllCategories();
    void  deleteCategory(Long id);
}
