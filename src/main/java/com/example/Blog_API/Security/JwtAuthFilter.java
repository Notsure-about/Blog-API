package com.example.Blog_API.Security;

import com.example.Blog_API.Entity.adminDetails;
import com.example.Blog_API.Repository.AdminDetailsRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private  final AdminDetailsRepository adminDetailsRepository;
    private final  AuthUtil authUtil;
    private  final HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{ log.info("incoming message: {}", request.getRequestURI() );
        final String requestTokenHeader = request.getHeader("Authorization");
        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
        return;
        }
        String token = requestTokenHeader.split("Bearer")[1]; // "Bearer split with token details in form of array"
        String username = authUtil.getUsernameFromToken(token);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            adminDetails details = adminDetailsRepository.findByUsername(username).orElseThrow();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken  =new UsernamePasswordAuthenticationToken( details, null, details.getAuthorities());
       SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        filterChain.doFilter(request,response);

    } catch (Exception e) {
            handlerExceptionResolver.resolveException(request,response,null,e);
        }
}
}
