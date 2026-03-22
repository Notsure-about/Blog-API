package com.example.Blog_API.Security;

import com.example.Blog_API.DTO.LoginRequestDto;
import com.example.Blog_API.DTO.LoginResponseDto;
import com.example.Blog_API.DTO.SignupResponseDto;
import com.example.Blog_API.Entity.adminDetails;
import com.example.Blog_API.Repository.AdminDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
private final AuthenticationManager authenticationManager;
private final AdminDetailsRepository adminDetailsRepository;
private final PasswordEncoder passwordEncoder;
private final AuthUtil authUtil;
public LoginResponseDto login(LoginRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())) ;
        adminDetails admin = (adminDetails) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(admin);
        return new LoginResponseDto(token, admin.getId());
    }

    public SignupResponseDto signup(LoginRequestDto dto) {
    adminDetails details = adminDetailsRepository.findByUsername(dto.getUsername()).orElse(null);
    if(details!=null) throw  new IllegalArgumentException("details already existed");
       details = adminDetailsRepository.save(adminDetails.builder()
             .username(dto.getUsername())
             .Password(passwordEncoder.encode(dto.getPassword()))
             .build()
     );
             return  new SignupResponseDto(details.getId(), details.getUsername());

    }
}
