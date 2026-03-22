package com.example.Blog_API.Controller;

import com.example.Blog_API.DTO.entityResponseDto;
import com.example.Blog_API.Service.entityResService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Response")
@AllArgsConstructor
public class entityResController {
    @Autowired
    private entityResService entityResService;
    @GetMapping("/posts")
  public ResponseEntity<entityResponseDto>getAllPosts(
          @RequestParam(value = "pageNumber" , defaultValue = "0") int pageNumber,
          @RequestParam(value = "pageSize" ,defaultValue = "5") int pageSize,
          @RequestParam(value = "sortBy" , defaultValue ="Id") String sortBy,
          @RequestParam(value = "sortDir" , defaultValue = "asc") String sortDir
    ){
     return ResponseEntity.ok(entityResService.getAllPosts(pageNumber, pageSize, sortBy, sortDir));
    }
    @GetMapping("/category")
    public ResponseEntity<entityResponseDto>getCategoryById(
           @PathVariable Long CategoryId,
           @RequestParam (defaultValue = "0") int page,
           @RequestParam(defaultValue = "0") int size
    ){
         return ResponseEntity.ok(entityResService.getCategoryByPosts(CategoryId,page,size));
    }
}
