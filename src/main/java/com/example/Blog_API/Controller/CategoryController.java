package com.example.Blog_API.Controller;


import com.example.Blog_API.DTO.CategoryDto;
import com.example.Blog_API.Exception.ApiResponse;
import com.example.Blog_API.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
@Autowired
    private CategoryService categoryService;
@PostMapping("/post")
private ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto){
return new ResponseEntity<>(categoryService.createCategory(dto), HttpStatus.CREATED);
}
@PutMapping("/{id}")
 private ResponseEntity<CategoryDto> update( @PathVariable Long id , @RequestBody CategoryDto dto){
  return ResponseEntity.ok(categoryService.updateCategory(id , dto));
}
@GetMapping("/get/{id}")
 private  ResponseEntity<CategoryDto> get(@PathVariable Long id){
    return ResponseEntity.ok(categoryService.getCategory(id));
}
 @GetMapping("/get")
    private  ResponseEntity<List<CategoryDto>> getAll(){
    return ResponseEntity.ok(categoryService.getAllCategories());
 }
 @DeleteMapping("/{id}")
    private ResponseEntity<ApiResponse> delete(@PathVariable Long id){
    categoryService.deleteCategory(id);
    return ResponseEntity.ok(new ApiResponse("Category Deleted" , true ));
 }
}
