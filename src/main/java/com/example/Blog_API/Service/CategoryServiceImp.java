package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.CategoryDto;
import com.example.Blog_API.Entity.Category;
import com.example.Blog_API.Exception.ResourceNotFoundException;
import com.example.Blog_API.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CategoryServiceImp  implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
    Category category =  modelMapper.map(dto, Category.class);  // remember this first make an object to store the value
    Category saved = categoryRepository.save(category); // now save the object in repository
        return modelMapper.map(category, CategoryDto.class); // return the object in the format
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Category cat = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id ));
//        cat.setDescription(dto.getDescription());
        modelMapper.map(dto , cat);
        Category update = categoryRepository.save(cat);
        return modelMapper.map(update, CategoryDto.class );

    }

    @Override
    public CategoryDto getCategory(Long id) {
       Category cat = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
       return  modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category , CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {
        Category cat =categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", " id" , id));
        categoryRepository.delete(cat);

    }
}
