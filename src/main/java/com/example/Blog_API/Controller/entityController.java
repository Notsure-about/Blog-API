package com.example.Blog_API.Controller;

import com.example.Blog_API.DTO.entityDto;
import com.example.Blog_API.Entity.entity;
import com.example.Blog_API.Exception.ApiResponse;
import com.example.Blog_API.Repository.entityRepository;
import com.example.Blog_API.Service.entityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entity")
@AllArgsConstructor
public class entityController {
    @Autowired
    private  entityService entityservice;

    @GetMapping("/get")
    public ResponseEntity<List<entityDto>> getAllPosts(){
    return ResponseEntity.ok(entityservice.getAllPosts());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<entityDto> getPost(@PathVariable Long id){
        return ResponseEntity.ok(entityservice.getPostById(id));
    }

    @PostMapping("/post")
    public ResponseEntity<entityDto> createPost(@RequestBody entityDto entityDto ){
        return new ResponseEntity<>(entityservice.CreatePost(entityDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<entityDto> updatePost(@PathVariable Long id , @RequestBody entityDto entityDto){
        return ResponseEntity.ok( entityservice.updatePost(id,entityDto ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long id){
        entityservice.deletePost(id);
        return ResponseEntity.ok(new ApiResponse("Post Deleted", true));
    }
}
