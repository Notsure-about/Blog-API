package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.CommentDto;
import com.example.Blog_API.Entity.Comment;
import com.example.Blog_API.Entity.User;
import com.example.Blog_API.Entity.entity;
import com.example.Blog_API.Exception.ResourceNotFoundException;
import com.example.Blog_API.Repository.CommentRepository;
import com.example.Blog_API.Repository.UserRepository;
import com.example.Blog_API.Repository.entityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private entityRepository entityRepository;
    @Autowired
    private ModelMapper modelMapper;
    private Comment convertToComment(CommentDto dto){
        return modelMapper.map(dto, Comment.class);
    }
    private CommentDto convertToDto(Comment comments){
        return modelMapper.map(comments, CommentDto.class);
    }
    @Override
    public CommentDto createComment(CommentDto dto, Long entityId, Long userId) {
       entity entity = entityRepository.findById(entityId).orElseThrow(()->new ResourceNotFoundException("entity" ,"id",entityId));
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException( "User", "id" , userId));
        Comment comment = convertToComment(dto);
        comment.setEntity(entity);
        comment.setUser(user);
        Comment saved = commentRepository.save(comment);
        return convertToDto(saved);
    }

    @Override
    public CommentDto updateComment(CommentDto dto, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment" , "id" ,commentId));
        comment.setContent(dto.getContent());
        Comment update = commentRepository.save(comment);
        return convertToDto(update);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment" , "id" ,commentId));
       commentRepository.delete(comment);

    }

    @Override
    public List<CommentDto> getCommentsByPost(Long entityId) {
        List<Comment> comments = commentRepository.findByEntityId(entityId);
        return comments.stream()
                .map(this::convertToDto)
                .toList();
    }
}
