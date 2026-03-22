package com.example.Blog_API.Service;


import com.example.Blog_API.DTO.UserDto;
import com.example.Blog_API.Entity.User;
import com.example.Blog_API.Exception.ResourceNotFoundException;
import com.example.Blog_API.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp  implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    private UserDto ConvertToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
    private User ConvertToUser(UserDto dto){
        return modelMapper.map(dto, User.class);
    }
    @Override
    public UserDto CreateUser(UserDto dto) {
      User user = ConvertToUser(dto);
      User saved = userRepository.save(user);
      return ConvertToDto(saved);
    }

    @Override
    public UserDto GetUserById(Long id) {
       User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
       return ConvertToDto(user);
    }

    @Override
    public UserDto UpdateUser(Long id, UserDto dto) {
       User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
       user.setEmail(dto.getEmail());
       user.setName(dto.getName());
       User update = userRepository.save(user);
       return ConvertToDto(update);
    }

    @Override
    public List<UserDto> GetAllUsers() {
        return userRepository.findAll().stream()
                .map(this::ConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void DeleteUser(Long id) {
     User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
     userRepository.delete(user);
    }
}
