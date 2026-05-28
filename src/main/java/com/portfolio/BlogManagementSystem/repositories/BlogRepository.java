package com.portfolio.BlogManagementSystem.repositories;

import com.portfolio.BlogManagementSystem.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
