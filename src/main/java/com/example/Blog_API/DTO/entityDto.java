package com.example.Blog_API.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class entityDto {
    private Long id;
    private String title;
    private String content;
    private Long categoryId;
}
