package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.UserDto;
import com.example.Blog_API.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    UserDto CreateUser(UserDto dto);
    UserDto GetUserById(Long id);
    UserDto UpdateUser(Long id, UserDto dto);
    List <UserDto> GetAllUsers();
    void DeleteUser(Long id);
}
