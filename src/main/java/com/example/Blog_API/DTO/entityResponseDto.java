package com.example.Blog_API.DTO;

import lombok.Data;

import java.util.List;
@Data

public class entityResponseDto {
        private List<entityDto> content;
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean lastPage;
    }


