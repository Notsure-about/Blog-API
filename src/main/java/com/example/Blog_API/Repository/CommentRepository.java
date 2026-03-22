package com.example.Blog_API.Repository;

import com.example.Blog_API.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByEntityId(Long entityId);
}
