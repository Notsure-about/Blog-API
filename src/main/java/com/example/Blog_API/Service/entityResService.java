package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.entityResponseDto;
import com.example.Blog_API.Entity.entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface entityResService {
    entityResponseDto getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
    entityResponseDto getCategoryByPosts(Long CategoryId , int page , int size) ;
}
