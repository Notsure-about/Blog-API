package com.example.Blog_API.Exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
     ApiResponse response = new ApiResponse(ex.getMessage(),false);
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AuthenticationException.class)
       public ResponseEntity<ApiError> authenticationException(AuthenticationException ex){
        ApiError error = new ApiError("Authentication Failed" + ex.getMessage() ,HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>( error , HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(JwtException.class)
    public  ResponseEntity<ApiError> jwtException(JwtException ex){
        ApiError error = new ApiError("Invalid Jwt Token " + ex.getMessage() , HttpStatus.UNAUTHORIZED  );
        return new ResponseEntity<>(error ,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public  ResponseEntity<ApiError> accessDeniedException(AccessDeniedException ex) {
        ApiError error = new ApiError( " Access Denied : Insufficient Permissions" + ex.getMessage() ,HttpStatus.FORBIDDEN );
        return new ResponseEntity<>( error, HttpStatus.FORBIDDEN);

    }
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ApiError> genericException(Exception ex){
        ApiError error = new ApiError(" An unexcepted error occured" + ex.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
