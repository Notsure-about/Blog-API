package com.example.Blog_API.Repository;

import com.example.Blog_API.Entity.entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface entityRepository extends JpaRepository<entity, Long> {
    Page<entity> findByCategory_Id(Long categoryId, Pageable pageable);
}
