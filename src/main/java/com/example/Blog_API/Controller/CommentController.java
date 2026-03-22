package com.example.Blog_API.Controller;

import com.example.Blog_API.DTO.CommentDto;
import com.example.Blog_API.Exception.ApiResponse;
import com.example.Blog_API.Service.CommentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
    @Autowired
    private CommentService commentService;
@PostMapping("/post/{entityId}/user/{userId}")
public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto, @PathVariable Long entityId ,@PathVariable Long userId){
   return new ResponseEntity<>(commentService.createComment(dto,entityId,userId), HttpStatus.CREATED);
}
@PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto dto ,@PathVariable Long commentId){
    return  ResponseEntity.ok(commentService.updateComment(dto,commentId));

}
@DeleteMapping("/{commentId}")
    public  ResponseEntity<ApiResponse> deleteComment(Long commentId){
         commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Comment Deleted" , true ));
    }
    @GetMapping("/post/{entityId}")
    public  ResponseEntity<List<CommentDto>> getCommentByPost(Long entityId){
    return ResponseEntity.ok(commentService.getCommentsByPost(entityId));

    }
}

