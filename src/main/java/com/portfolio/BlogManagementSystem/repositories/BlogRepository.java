package com.portfolio.BlogManagementSystem.repositories;

import com.portfolio.BlogManagementSystem.entities.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Page<Blog> findAllByUserId(Long userId, Pageable pageable);

    Optional<Blog> findByIdAndUserId(Long blogId, Long userId);
}
