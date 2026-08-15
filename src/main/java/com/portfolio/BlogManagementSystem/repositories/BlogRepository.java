package com.portfolio.BlogManagementSystem.repositories;

import com.portfolio.BlogManagementSystem.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findAllByUserId(Long userId);
}
