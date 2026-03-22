package com.example.Blog_API.Controller;

import com.example.Blog_API.DTO.UserDto;
import com.example.Blog_API.Exception.ApiResponse;
import com.example.Blog_API.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    @Autowired
private UserService userService;
    @PostMapping("/post")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto dto){
        return new ResponseEntity<>(userService.CreateUser(dto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable Long id , @RequestBody UserDto dto){
        return  ResponseEntity.ok(userService.UpdateUser(id,dto));
    }
    @GetMapping("/get/{id}")
    ResponseEntity<UserDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.GetUserById(id));
    }
    @GetMapping("/get")
    ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userService.GetAllUsers());
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id){
      userService.DeleteUser(id);
      return ResponseEntity.ok(new ApiResponse("User Deleted", true));
    }

}

