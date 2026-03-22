package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.entityDto;
import com.example.Blog_API.DTO.entityResponseDto;
import com.example.Blog_API.Entity.Category;
import com.example.Blog_API.Entity.entity;
import com.example.Blog_API.Repository.CategoryRepository;
import com.example.Blog_API.Repository.entityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class entityResServiceImp implements entityResService{
@Autowired
  private entityRepository  entityRepository;
@Autowired
  private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    private entityDto convertToDto(entity entity){
        return modelMapper.map(entity,entityDto.class);
    }
    private  entity converToentity(entityDto entityDto){
        return modelMapper.map(entityDto,entity.class);
    }

    @Override
    public entityResponseDto getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
         Page<entity> entityPage = entityRepository.findAll(pageable);
        List<entityDto> entityDtos = entityPage.getContent()
                .stream()
                .map(entity -> convertToDto(entity))
                .toList();
        entityResponseDto response = new entityResponseDto();
        response.setContent(entityDtos);
        response.setPageNumber(entityPage.getNumber());
        response.setPageSize(entityPage.getSize());
        response.setTotalElements(entityPage.getTotalElements());
        response.setTotalPages(entityPage.getTotalPages());
        response.setLastPage(entityPage.isLast());
       return response;

    }

    @Override
    public entityResponseDto getCategoryByPosts(Long CategoryId,int page ,int size) {
        Category category = categoryRepository.findById(CategoryId).orElseThrow();
        Pageable pageable1 = PageRequest.of(page,size);
        Page<entity>entityPage = entityRepository.findByCategory_Id(CategoryId,pageable1);
        List<entityDto>entityDtos = entityPage.getContent()
                .stream()
                .map(entity -> convertToDto(entity))
                .toList();
        //fill response object
        entityResponseDto response = new entityResponseDto();
        response.setContent(entityDtos);
        response.setPageNumber(entityPage.getNumber());
        response.setPageSize(entityPage.getSize());
        response.setTotalPages(entityPage.getTotalPages());
        response.setTotalElements(entityPage.getTotalElements());
        response.setLastPage(entityPage.isLast());
        return response;

    }
}