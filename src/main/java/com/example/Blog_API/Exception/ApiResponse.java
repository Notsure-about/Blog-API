package com.example.Blog_API.Exception;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse {
    private  String message;
    private  boolean success;
    private LocalDateTime time;
    public ApiResponse(){this.time = LocalDateTime.now();
    }

    public ApiResponse(String message, boolean b) {
     this();
     this.message = message;
     this.success = b;
    }
}
