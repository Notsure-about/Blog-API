package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.entityDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface entityService {
    entityDto CreatePost(entityDto entityDto);
    entityDto getPostById(Long id);
    List<entityDto> getAllPosts();
    entityDto updatePost(Long id,entityDto entityDto );
    void deletePost(Long id);
}
