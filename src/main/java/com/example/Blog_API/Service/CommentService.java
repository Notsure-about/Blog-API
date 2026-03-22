package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    CommentDto createComment(CommentDto dto, Long entityId, Long userId);
    CommentDto updateComment(CommentDto dto, Long commentId);
    void deleteComment(Long commentId);
    List<CommentDto> getCommentsByPost(Long entityId);
}
