package com.example.Blog_API.Repository;

import com.example.Blog_API.Entity.adminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface AdminDetailsRepository extends JpaRepository<adminDetails, Long> {
    Optional<adminDetails> findByUsername(String username);
}