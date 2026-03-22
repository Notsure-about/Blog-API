package com.example.Blog_API.Repository;

import com.example.Blog_API.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
