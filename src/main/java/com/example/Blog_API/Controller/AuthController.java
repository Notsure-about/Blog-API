package com.example.Blog_API.Controller;

import com.example.Blog_API.DTO.LoginRequestDto;
import com.example.Blog_API.DTO.LoginResponseDto;
import com.example.Blog_API.DTO.SignupResponseDto;
import com.example.Blog_API.Security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
 private final AuthService authService;
 @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
     return ResponseEntity.ok(authService.login(dto));
 }
 @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginRequestDto dto){
     return ResponseEntity.ok(authService.signup(dto));

 }
}
